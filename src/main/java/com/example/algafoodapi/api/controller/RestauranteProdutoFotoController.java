package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 31/07/2020 - {20:46}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.FotoProdutoModelAssembler;
import com.example.algafoodapi.api.model.FotoProdutoModel;
import com.example.algafoodapi.api.model.input.FotoProdutoInput;
import com.example.algafoodapi.dominio.modelo.FotoProduto;
import com.example.algafoodapi.dominio.modelo.Produto;
import com.example.algafoodapi.dominio.service.CadastroProdutoService;
import com.example.algafoodapi.dominio.service.CatalogoFotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService fotoProdutoService;

    @Autowired
    private CadastroProdutoService produtoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setProduto(produto);
        fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
        fotoProduto.setContentType(arquivo.getContentType());
        fotoProduto.setTamanho(arquivo.getSize());
        fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = fotoProdutoService.salvar(fotoProduto, arquivo.getInputStream());
        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }
}
