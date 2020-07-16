package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 23/06/2020 - {10:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.FormaPagamentoNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Estado;
import com.example.algafoodapi.dominio.modelo.FormaPagamento;
import com.example.algafoodapi.dominio.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroFormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long id){
        try{
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        }
    }

    public FormaPagamento buscarOuFalhar(Long idFormaPagamento) {
        return formaPagamentoRepository.findById(idFormaPagamento)
                .orElseThrow(()-> new FormaPagamentoNaoEncontradaException(idFormaPagamento));
    }
}
