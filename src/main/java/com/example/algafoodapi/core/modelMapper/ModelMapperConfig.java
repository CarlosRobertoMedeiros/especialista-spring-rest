package com.example.algafoodapi.core.modelMapper;
/*
 *  @criado em: 15/07/2020 - {07:05}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.model.EnderecoModel;
import com.example.algafoodapi.api.v1.model.input.ItemPedidoInput;
import com.example.algafoodapi.api.v2.model.input.CidadeInputV2;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.modelo.Endereco;
import com.example.algafoodapi.dominio.modelo.ItemPedido;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));
//        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);

        TypeMap<Endereco,EnderecoModel> enderecoToEnderecoModelTypeMap =  modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }

}
