package com.example.demo;

import com.example.demo.ConsumoEnergetico;
import com.example.demo.ConsumoEnergeticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoEnergeticoService {
    private final ConsumoEnergeticoRepository repository;

    public ConsumoEnergeticoService(ConsumoEnergeticoRepository repository) {
        this.repository = repository;
    }

    public List<ConsumoEnergetico> listarTodos() {
        return repository.findAll();
    }

    public List<ConsumoEnergetico> listarPorEndereco(String endereco) {
        return repository.findAllByEndereco(endereco);
    }

    public ConsumoEnergetico salvar(ConsumoEnergetico consumoEnergetico) {
        return repository.save(consumoEnergetico);
    }
}