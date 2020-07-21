package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 18/07/2020 - {23:42}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.GrupoModel;
import com.example.algafoodapi.dominio.modelo.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toModel(grupo))
                .collect(Collectors.toList());
    }

}
