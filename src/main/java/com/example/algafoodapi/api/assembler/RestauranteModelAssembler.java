package com.example.algafoodapi.api.assembler;/*
 *  @criado em: 14/07/2020 - {15:20}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.CozinhaModel;
import com.example.algafoodapi.api.model.RestauranteModel;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    public RestauranteModel toModel(Restaurante restaurante) {
        CozinhaModel cozinhaModel = new CozinhaModel();
        RestauranteModel restauranteModel = new RestauranteModel();

        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinhaModel(cozinhaModel);

        return restauranteModel;
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }


}
