package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoRepository.findAll(); // Busca todos os produtos do banco
        model.addAttribute("produtos", produtos); // Adiciona os produtos ao modelo
        return "produtos-listar"; // Nome do template Thymeleaf para listar produtos
    }

    @GetMapping("/adicionar")
    public String adicionarProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos-adicionar"; // Nome do template Thymeleaf para adicionar produto
    }

    @PostMapping("/adicionar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos/listar"; // Redireciona para a lista de produtos após salvar
    }
}
