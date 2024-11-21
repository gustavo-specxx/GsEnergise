package com.example.demo;

import com.example.demo.ConsumoEnergeticoDTO;
import com.example.demo.ConsumoEnergetico;
import com.example.demo.ChatGPTService;
import com.example.demo.ConsumoEnergeticoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/consumo")
public class ConsumoEnergeticoController {
    private final ConsumoEnergeticoService consumoService;
    private final ChatGPTService chatGPTService;

    public ConsumoEnergeticoController(ConsumoEnergeticoService consumoService, ChatGPTService chatGPTService) {
        this.consumoService = consumoService;
        this.chatGPTService = chatGPTService;
    }

    @GetMapping
    public String listar(Model model) {
        List<ConsumoEnergetico> consumos = consumoService.listarTodos();
        model.addAttribute("consumos", consumos);
        return "consumo/listar";
    }

    @PostMapping
    public String salvar(@ModelAttribute @Valid ConsumoEnergeticoDTO consumoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "consumo/form";
        }

        ConsumoEnergetico consumo = new ConsumoEnergetico();
        consumo.setEndereco(consumoDTO.getEndereco());
        consumo.setConsumo(consumoDTO.getConsumo());
        consumo.setDataRegistro(consumoDTO.getDataRegistro());

        consumoService.salvar(consumo);
        return "redirect:/consumo";
    }

    @GetMapping("/recomendacoes")
    public String obterRecomendacoes(Model model) {
        List<ConsumoEnergetico> consumos = consumoService.listarTodos();
        String prompt = "Baseado nos seguintes dados de consumo: " + consumos +
                ", quais sugestões você daria para melhorar a eficiência energética?";
        String recomendacao = chatGPTService.gerarInsight(prompt);
        model.addAttribute("recomendacao", recomendacao);
        return "consumo/recomendacoes";
    }
}