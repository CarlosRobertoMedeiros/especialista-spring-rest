package com.example.algafoodapi.dominio.repository;
/*
 *  @criado em: 19/07/2020 - {11:48}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

}
