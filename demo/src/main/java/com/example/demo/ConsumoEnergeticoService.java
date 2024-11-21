package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsumoEnergeticoService {

    private final ConsumoEnergeticoRepository consumoRepository;

    public ConsumoEnergeticoService(ConsumoEnergeticoRepository consumoRepository) {
        this.consumoRepository = consumoRepository;
    }

    public List<ConsumoEnergetico> listarTodos() {
        return consumoRepository.findAll();
    }

    public void salvar(ConsumoEnergeticoDTO consumoDTO) {
        // Convertendo DTO para entidade antes de salvar
        ConsumoEnergetico consumo = new ConsumoEnergetico();
        consumo.setEndereco(consumoDTO.getEndereco());
        consumo.setConsumo(consumoDTO.getConsumo());
        consumo.setDataRegistro(consumoDTO.getDataRegistro());

        consumoRepository.save(consumo); // Salva a entidade no banco
    }
}
