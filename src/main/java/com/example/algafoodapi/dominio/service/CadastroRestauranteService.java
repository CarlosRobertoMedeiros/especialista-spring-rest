package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 23/06/2020 - {08:04}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.RestauranteNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.FormaPagamento;
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

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return  restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.inativar();
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);

    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);

    }

    @Transactional
    public void abrir(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.fechar();
    }


    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(()-> new RestauranteNaoEncontradaException(id));
    }
}
