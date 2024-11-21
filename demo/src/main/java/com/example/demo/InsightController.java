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
        String insight = chatGPTService.gerarInsight(mensagem);
        model.addAttribute("mensagem", mensagem);
        model.addAttribute("insight", insight);
        return "insights";
    }
}
