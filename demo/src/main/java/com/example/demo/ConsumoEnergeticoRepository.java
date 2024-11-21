package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoEnergeticoRepository extends JpaRepository<ConsumoEnergetico, Long> {

    // Consulta por endereço, caso necessário
    List<ConsumoEnergetico> findAllByEndereco(String endereco);
}
