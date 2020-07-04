package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 23/06/2020 - {07:45}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.CozinhaNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.RestauranteRepository;
import com.example.algafoodapi.dominio.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes" , produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarTodos(){
        return  restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable Long id){
        return restauranteService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        try {
            return restauranteService.salvar(restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@RequestBody Restaurante restaurante,
                                                 @PathVariable Long id){

            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formasdePagamento", "endereco","dataCadastro", "produtos");
            try{
                return restauranteService.salvar(restauranteAtual);
            }catch (EntidadeNaoEncontradaException e){
                throw new NegocioException(e.getMessage());
            }
    }


    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String,Object> campos){

        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
        merge(campos, restauranteAtual);
        return atualizar(restauranteAtual,restauranteId);
    }

    private void merge(@RequestBody Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        //A conversão com Object Mapper é necessária
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade,valorPropriedade)->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);//Quebrando o Acesso do atributo privado
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            System.out.println(nomePropriedade+" - "+valorPropriedade+" - "+ novoValor);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
