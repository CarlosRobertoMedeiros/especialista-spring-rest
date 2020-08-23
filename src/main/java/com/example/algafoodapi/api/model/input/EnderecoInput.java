package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 18/07/2020 - {23:00}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.CidadeResumoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "38400-000", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "SQN", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "\"402\"", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Bloco X Apto Z")
    private String complemento;

    @ApiModelProperty(example = "Asa Norte", required = true)
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
