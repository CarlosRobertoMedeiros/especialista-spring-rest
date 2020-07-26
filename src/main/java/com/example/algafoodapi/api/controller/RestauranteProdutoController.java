package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 20/07/2020 - {13:11}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.ProdutoInputDisassembler;
import com.example.algafoodapi.api.assembler.ProdutoModelAssembler;
import com.example.algafoodapi.api.model.ProdutoModel;
import com.example.algafoodapi.api.model.input.ProdutoInput;
import com.example.algafoodapi.dominio.modelo.Produto;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.repository.ProdutoRepository;
import com.example.algafoodapi.dominio.service.CadastroProdutoService;
import com.example.algafoodapi.dominio.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService produtoService;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = null;

        if (incluirInativos){
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);

        }else{
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }
        return produtoModelAssembler.toCollectionModel(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtoService.salvar(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }


}
