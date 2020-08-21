package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 16/07/2020 - {09:49}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.example.algafoodapi.api.assembler.FormaPagamentoModelAssembler;
import com.example.algafoodapi.api.model.FormaPagamentoModel;
import com.example.algafoodapi.api.model.input.FormaPagamentoInput;
import com.example.algafoodapi.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.example.algafoodapi.dominio.modelo.FormaPagamento;
import com.example.algafoodapi.dominio.repository.FormaPagamentoRepository;
import com.example.algafoodapi.dominio.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi{

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {

        //Tem que desabilitar o shallow para implementar o deepTag
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag ="0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if(dataUltimaAtualizacao!=null){
            eTag =  String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        //já temos condições de saber se continuamos ou não o processamento
        if (request.checkNotModified(eTag)){
            return  null;
        }

        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
        List<FormaPagamentoModel> formasPagamentosModel = formaPagamentoModelAssembler
                .toCollectionModel(todasFormasPagamentos);

        return  ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosModel);

    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String eTag = "0";

        OffsetDateTime dataAtualizacao = formaPagamentoRepository
                .getDataAtualizacaoById(formaPagamentoId);

        if (dataAtualizacao != null) {
            eTag = String.valueOf(dataAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);

        return  ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .body(formaPagamentoModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = formaPagamentoService.salvar(formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }

}
