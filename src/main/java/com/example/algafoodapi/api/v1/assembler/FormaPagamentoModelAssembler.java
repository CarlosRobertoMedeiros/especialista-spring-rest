package com.example.algafoodapi.api.v1.assembler;
/*
 *  @criado em: 15/07/2020 - {08:13}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import com.example.algafoodapi.api.v1.controller.FormaPagamentoController;
import com.example.algafoodapi.api.v1.model.FormaPagamentoModel;
import com.example.algafoodapi.core.security.AlgaSecurity;
import com.example.algafoodapi.dominio.modelo.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelAssembler
        extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);
    }

    @Override
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        FormaPagamentoModel formaPagamentoModel =
                createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModel);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        }

        return formaPagamentoModel;
    }

    @Override
    public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        CollectionModel<FormaPagamentoModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(algaLinks.linkToFormasPagamento());
        }

        return collectionModel;
    }
}
