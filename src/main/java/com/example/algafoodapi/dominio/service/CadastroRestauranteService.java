package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 23/06/2020 - {08:04}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.RestauranteNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return  restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(()-> new RestauranteNaoEncontradaException(id));
    }
}
