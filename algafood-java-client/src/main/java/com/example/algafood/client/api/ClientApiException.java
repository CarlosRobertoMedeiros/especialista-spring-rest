package com.example.algafood.client.api;
/*
 *  @criado em: 14/08/2020 - {06:32}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafood.client.model.RestauranteResumoModel;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class ClientApiException extends RuntimeException {

    public ClientApiException(String mensagem, RestClientResponseException cause){
        super(message, cause);

    }





}
