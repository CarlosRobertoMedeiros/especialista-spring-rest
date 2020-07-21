package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 19/07/2020 - {10:15}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.UsuarioModel;
import com.example.algafoodapi.dominio.modelo.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario){
        return modelMapper.map(usuario,UsuarioModel.class);
    }

    public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios){
        return usuarios.stream()
                .map(usuario -> toModel(usuario))
                .collect(Collectors.toList());
    }
}
