package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 27/06/2020 - {10:23}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_grupo", schema = "app")
public class Grupo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_grupo_permissao",
               joinColumns = @JoinColumn(name = "id_Grupo"),
               inverseJoinColumns = @JoinColumn(name = "id_Permissao")
    )
    List<Permissao> permissoes = new ArrayList<>();

}
