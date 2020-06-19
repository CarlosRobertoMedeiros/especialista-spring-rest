package com.example.algafoodapi.infraestrutura.repository;/*
 *  @criado em: 18/06/2020 - {10:03}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Restaurante> listar() {
        return manager
                .createQuery("from Restaurante")
                .getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return  manager.find(Restaurante.class,id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = this.buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
