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
import com.example.algafoodapi.dominio.exception.CozinhaNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import com.example.algafoodapi.dominio.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping
    public List<RestauranteModel> listarTodos(){
        return  restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
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
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteModel atualizar(@RequestBody @Valid RestauranteInput restauranteInput,
                                                 @PathVariable Long id){

        Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formasdePagamento", "endereco","dataCadastro", "produtos");
            try{
                return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
            }catch (EntidadeNaoEncontradaException e){
                throw new NegocioException(e.getMessage());
            }
    }


    private Restaurante toDomainObject(RestauranteInput restauranteInput){

        Restaurante restaurante = new Restaurante();
        Cozinha cozinha = new Cozinha();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        cozinha.setId(restauranteInput.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }

}
