package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InsightController {

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/insights")
    public String showInsightPage() {
        return "insights";
    }

    @PostMapping("/insights")
    public String generateInsight(@RequestParam("mensagem") String mensagem, Model model) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            model.addAttribute("insight", "Por favor, insira uma mensagem válida.");
            return "insights";
        }

        try {
            String insight = chatGPTService.gerarInsight(mensagem.trim());
            model.addAttribute("mensagem", mensagem);
            model.addAttribute("insight", insight);
        } catch (Exception e) {
            model.addAttribute("insight", "Erro ao gerar o insight: " + e.getMessage());
        }

        return "insights";
    }
}
