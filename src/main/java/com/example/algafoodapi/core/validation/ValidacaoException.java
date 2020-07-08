package com.example.algafoodapi.core.validation;/*
 *  @criado em: 08/07/2020 - {08:03}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException{

    private BindingResult bindingResult;
}
