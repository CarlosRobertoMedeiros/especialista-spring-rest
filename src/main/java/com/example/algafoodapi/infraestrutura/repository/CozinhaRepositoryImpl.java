package com.example.algafoodapi.infraestrutura.repository;
/*
 *  @criado em: 18/06/2020 - {09:13}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        return manager
                .createQuery("from Cozinha",Cozinha.class)
                .getResultList();
    }

    @Override
    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class,id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha){
        cozinha = this.buscar(cozinha.getId());
        manager.remove(cozinha);
    }
}
