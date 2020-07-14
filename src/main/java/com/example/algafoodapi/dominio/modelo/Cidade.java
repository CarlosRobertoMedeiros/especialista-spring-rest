package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 19/06/2020 - {13:06}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_cidade", schema = "app")
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    //@JsonIgnoreProperties(value = "nome", allowGetters = true)
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

}
