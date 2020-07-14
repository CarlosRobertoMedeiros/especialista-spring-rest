package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 14/07/2020 - {15:26}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.input.RestauranteInput;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {
    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }



}
