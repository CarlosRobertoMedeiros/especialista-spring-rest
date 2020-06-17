package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 16/06/2020 - {20:17}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_cozinha", schema = "app")
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
