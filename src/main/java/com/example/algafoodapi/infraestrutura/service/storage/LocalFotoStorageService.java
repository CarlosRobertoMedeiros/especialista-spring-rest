package com.example.algafoodapi.infraestrutura.service.storage;
/*
 *  @criado em: 02/08/2020 - {09:48}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFotoStorageService  implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            return Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));

        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar o arquivo. ",e);
        }

    }

    @Override
    public void remover(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);

        try {
            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo. ",e);
        }

    }

    private Path getArquivoPath(String nomeArquivo){
        return diretorioFotos.resolve(Paths.get(nomeArquivo));

    }

}
