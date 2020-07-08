package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 16/06/2020 - {20:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.core.validation.Groups;
import com.example.algafoodapi.core.validation.Multiplo;
import com.example.algafoodapi.core.validation.TaxaFrete;
import com.example.algafoodapi.core.validation.ValorZeroIncluiDescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@ValorZeroIncluiDescricao(valorField="taxaFrete",
        decricaoField="nome", descricaoObrigatoria="Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="tb_restaurante", schema = "app")
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

//    @DecimalMin("0")
    @NotNull
    //@PositiveOrZero(message = "{TaxaFrete.invalida}")
    @Multiplo(numero=5)
    @Column(name="taxa_frete" , nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnore
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_restaurante_forma_pagamento",
               joinColumns = @JoinColumn(name = "restaurante_id"),
               inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasdePagamento = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
