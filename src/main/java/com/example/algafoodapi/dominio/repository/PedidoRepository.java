package com.example.algafoodapi.dominio.repository;
/*
 *  @criado em: 21/07/2020 - {06:53}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
        JpaSpecificationExecutor<Pedido> {

    //@Query("from Pedido  where codigo = :codigo")
    Optional<Pedido> findByCodigo(String codigo);

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);

}
