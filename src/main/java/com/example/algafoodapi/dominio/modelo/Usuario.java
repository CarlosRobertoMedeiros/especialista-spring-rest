package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 27/06/2020 - {10:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_usuario" , schema = "app")
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "tb_usuario_grupo",
               joinColumns = @JoinColumn(name = "id_Usuario"),
               inverseJoinColumns = @JoinColumn(name = "id_Grupo"))
    private List<Grupo> grupos = new ArrayList<>();

}
