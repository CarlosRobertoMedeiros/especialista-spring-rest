package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 16/06/2020 - {20:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*@ValorZeroIncluiDescricao(valorField="taxaFrete",
        decricaoField="nome", descricaoObrigatoria="Frete Grátis")*/
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="tb_restaurante", schema = "app")
//As anotações comentadas estão sendo seguidas baseando nos Dtos(Input)
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    @Column(nullable = false)
    private String nome;

//    @DecimalMin("0")
//    @NotNull
//    @PositiveOrZero//(message = "{TaxaFrete.invalida}")
    //@Multiplo(numero=5)
    @Column(name="taxa_frete" , nullable = false)
    private BigDecimal taxaFrete;

//    @Valid
//    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//    @NotNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "tb_restaurante_forma_pagamento",
               joinColumns = @JoinColumn(name = "restaurante_id"),
               inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasdePagamento = new HashSet<>();


    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento){
        return getFormasdePagamento().remove(formaPagamento);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento){
        return getFormasdePagamento().add(formaPagamento);
    }

    public void abrir(){
        setAberto(true);
    }
    public void fechar(){
        setAberto(false);
    }

}
