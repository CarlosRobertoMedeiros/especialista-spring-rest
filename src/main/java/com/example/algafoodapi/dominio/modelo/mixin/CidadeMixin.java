package com.example.algafoodapi.dominio.modelo.mixin;
/*
 *  @criado em: 13/07/2020 - {06:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.core.validation.Groups;
import com.example.algafoodapi.dominio.modelo.Estado;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
