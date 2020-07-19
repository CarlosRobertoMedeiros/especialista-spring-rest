package com.example.algafoodapi.dominio.repository;
/*
 *  @criado em: 19/07/2020 - {10:21}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CustomJpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
}
