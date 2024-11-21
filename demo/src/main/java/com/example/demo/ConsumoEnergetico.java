package com.example.demo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consumo_energetico")
public class ConsumoEnergetico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo") // Mapear para a coluna existente no banco
    private Long id;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private Double consumo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_registro") // Mapear para a coluna no banco
    private Date dataRegistro;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
