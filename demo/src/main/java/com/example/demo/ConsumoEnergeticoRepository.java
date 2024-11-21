package com.example.demo;

import com.example.demo.ConsumoEnergetico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoEnergeticoRepository extends JpaRepository<ConsumoEnergetico, Long> {
    List<ConsumoEnergetico> findAllByEndereco(String endereco);
}
