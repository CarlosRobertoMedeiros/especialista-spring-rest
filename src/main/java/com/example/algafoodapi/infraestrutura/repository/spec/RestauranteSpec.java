package com.example.algafoodapi.infraestrutura.repository.spec;
/*
 *  @criado em: 26/06/2020 - {20:39}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpec {

    public static Specification<Restaurante> comFreteGratis(){
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome){
        return (root , query , builder )-> builder.like(root.get("nome"), "%"+ nome +"%");
    }

}
