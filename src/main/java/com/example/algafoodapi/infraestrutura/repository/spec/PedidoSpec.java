package com.example.algafoodapi.infraestrutura.repository.spec;
/*
 *  @criado em: 26/06/2020 - {20:39}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

import java.util.ArrayList;


public class PedidoSpec {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro){
        Specification<Pedido> pedido = (root, query, builder) -> {
            //N+1
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            ArrayList<Predicate> predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return pedido;
    }


}
