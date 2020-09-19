package com.example.algafoodapi.dominio.repository;
/*
 *  @criado em: 18/06/2020 - {10:01}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante,Long>, RestauranteRepositoryQueries,
                                                JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinha);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

    boolean existsResponsavel(Long restauranteId, Long usuarioId);


}
