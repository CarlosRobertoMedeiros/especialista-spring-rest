package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 31/07/2020 - {20:46}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.input.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId,
                              @Valid FotoProdutoInput fotoProdutoInput){

        String nomeArquivo = UUID.randomUUID().toString()
                +"_"+ fotoProdutoInput.getArquivo().getOriginalFilename();

        Path arquivoFoto = Paths.get("/home/roberto/Área de Trabalho/catalogo", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        try{
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
