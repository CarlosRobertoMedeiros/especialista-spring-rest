package com.example.algafoodapi.infraestrutura.service;
/*
 *  @criado em: 28/07/2020 - {06:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.dto.VendaDiaria;
import com.example.algafoodapi.dominio.service.VendaQueryService;
import jdk.nashorn.internal.objects.NativeDate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository //Ficou Desorganizado, por não ser um repositório
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
        Root<Pedido> root = query.from(Pedido.class);
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

        Expression<?> functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"), builder.literal(NativeDate.getTimezoneOffset(this)));

        Expression<?> functionDateDataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));


        Selection selection =  builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

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

        query.select(selection);

        query.where(predicates.toArray(new Predicate[0]));

        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
