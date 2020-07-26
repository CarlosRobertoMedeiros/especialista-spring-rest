package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 23/06/2020 - {07:45}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.RestauranteInputDisassembler;
import com.example.algafoodapi.api.assembler.RestauranteModelAssembler;
import com.example.algafoodapi.api.model.RestauranteModel;
import com.example.algafoodapi.api.model.input.RestauranteInput;
import com.example.algafoodapi.api.model.view.RestauranteView;
import com.example.algafoodapi.dominio.exception.*;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import com.example.algafoodapi.dominio.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes" , produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

//    @GetMapping
//    public MappingJacksonValue listarTodos(@RequestParam(required = false) String projecao){
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//
//        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//        if ("apenas-nome".equals(projecao)){
//            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//        } else if("completo".equals(projecao)){
//            restaurantesWrapper.setSerializationView(null);
//        }
//        return restaurantesWrapper;
//    }

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public List<RestauranteModel> listarTodos(){
        return  restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteModel> listarApenasNome(){
        return  listarTodos();
    }

    @GetMapping("/{id}")
    public RestauranteModel buscar(@PathVariable Long id){
        Restaurante restaurante = restauranteService.buscarOuFalhar(id);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput){
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteModel atualizar(@RequestBody @Valid RestauranteInput restauranteInput,
                                                 @PathVariable Long id){

        try{

             Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
             restauranteInputDisassembler.copyToDomainObject(restauranteInput,restauranteAtual);
             return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));

        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{idRestaurante}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long idRestaurante){
        restauranteService.ativar(idRestaurante);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarEmMassa(@RequestBody List<Long> restauranteIds){
        try{
            restauranteService.ativar(restauranteIds);
        }catch (RestauranteNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @DeleteMapping("/{idRestaurante}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long idRestaurante){
        restauranteService.inativar(idRestaurante);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarEmMassa(@RequestBody List<Long> restauranteIds){
        try {
            restauranteService.inativar(restauranteIds);
        }catch (RestauranteNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }

}
