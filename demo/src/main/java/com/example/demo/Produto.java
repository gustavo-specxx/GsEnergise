package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double consumoEnergetico;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public void setConsumoEnergetico(Double consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }
}
