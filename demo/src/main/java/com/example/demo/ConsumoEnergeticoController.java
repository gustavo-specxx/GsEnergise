package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "/consumo";
    }

    @PostMapping
    public String salvar(@ModelAttribute @Valid ConsumoEnergeticoDTO consumoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/consumo";
        }

        consumoService.salvar(consumoDTO);
        return "redirect:/consumo";
    }

    @GetMapping("/recomendacoes")
    public String obterRecomendacoes(Model model) {
        List<ConsumoEnergetico> consumos = consumoService.listarTodos();
        String prompt = "Baseado nos seguintes dados de consumo: " + consumos +
                ", quais sugestões você daria para melhorar a eficiência energética?";
        String recomendacao = chatGPTService.gerarInsight(prompt);
        model.addAttribute("recomendacao", recomendacao);
        return "/consumo";
    }
}
