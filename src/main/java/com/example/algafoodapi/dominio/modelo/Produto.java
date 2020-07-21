package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 27/06/2020 - {09:05}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco = new BigDecimal(String.valueOf(BigDecimal.ZERO));

    @Column(nullable = false)
    private boolean ativo=true;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

}
