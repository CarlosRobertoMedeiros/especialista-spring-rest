package com.example.algafoodapi.infraestrutura.repository;
/*
 *  @criado em: 26/06/2020 - {17:06}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import com.example.algafoodapi.dominio.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.algafoodapi.infraestrutura.repository.spec.RestauranteSpec.comFreteGratis;
import static com.example.algafoodapi.infraestrutura.repository.spec.RestauranteSpec.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy //Quebra a Referencia Circular Só instancia quando precisa
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> find(String nome,
                                  BigDecimal taxaFreteInicial,
                                  BigDecimal taxaFreteFinal ){

        //Utilizando JPQL
        /*
        StringBuilder sb = new StringBuilder();
        Map<String, Object> parametros = new HashMap<String, Object>();

        sb.append("from Restaurante where 0 = 0 ");

        if (StringUtils.hasLength(nome)){
            sb.append(" and nome like :nome ");
            parametros.put("nome","%" + nome + "%");
        }

        if (taxaFreteInicial!=null){
            sb.append(" and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial",taxaFreteInicial);
        }

        if (taxaFreteFinal!=null){
            sb.append(" and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal",taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = manager.createQuery(sb.toString(),Restaurante.class);

        parametros.forEach((chave,valor)->query.setParameter(chave,valor));

        return query.getResultList();
        */

        //Utilizando Criteria

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root =  criteria.from(Restaurante.class); //From Restaurante

        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(nome)){
            predicates.add(builder.like(root.get("nome"),"%"+ nome+"%"));
        }

        if (taxaFreteInicial!=null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"),taxaFreteInicial));
        }

        if (taxaFreteFinal!=null){
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"),taxaFreteFinal));
        }

        //Convertendo Um List para Um Array, basta criar um array vazio
        //Para retornar 1 instancia de array preenchido para todas as instancias
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().
                                    and(comNomeSemelhante(nome)));
    }


}
