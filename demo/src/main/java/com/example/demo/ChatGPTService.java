package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {

    private final RestTemplate restTemplate;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${chatgpt.api.url}")
    private String apiUrl;

    // Construtor para injetar o RestTemplate
    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Gera insights com base na mensagem enviada.
     *
     * @param mensagem Mensagem de entrada para o ChatGPT.
     * @return Resposta gerada pelo ChatGPT ou mensagem de erro.
     */
    public String gerarInsight(String mensagem) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "A chave da API do OpenAI não está configurada.";
        }

        System.out.println("API Key usada: " + apiKey);
        System.out.println("URL da API: " + apiUrl);

        // Configurando os headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        // Configurando o corpo da requisição
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo"); // ou "gpt-4"
        requestBody.put("messages", List.of(
                Map.of(
                        "role", "user",
                        "content", mensagem
                )
        ));

        System.out.println("Corpo da requisição: " + requestBody);

        // Enviando a requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);
            System.out.println("Resposta recebida: " + response.getBody());

            // Processando a resposta
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, String> message = (Map<String, String>) choice.get("message");
                    return message.get("content");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao se conectar com a API: " + e.getMessage();
        }

        return "Não foi possível gerar o insight no momento.";
    }
}
