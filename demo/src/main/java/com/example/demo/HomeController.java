package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/consumo-energetico")
    public String consumoEnergetico() {
        return "consumo-energetico";
    }

    @GetMapping("/insights-chatgpt")
    public String insightsChatGPT() {
        return "insights-chatgpt";
    }
}
