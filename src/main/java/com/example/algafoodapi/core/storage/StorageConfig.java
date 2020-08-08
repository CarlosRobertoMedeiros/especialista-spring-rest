package com.example.algafoodapi.core.storage;
/*
 *  @criado em: 08/08/2020 - {14:23}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.algafoodapi.dominio.service.FotoStorageService;
import com.example.algafoodapi.infraestrutura.service.storage.LocalFotoStorageService;
import com.example.algafoodapi.infraestrutura.service.storage.S3FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.tipo", havingValue = "s3")
    public AmazonS3 amazonS3(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(storageProperties.getS3().getIdChaveAcesso(),
                                storageProperties.getS3().getChaveAcessoSecreta());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService(){

        if (StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())){
            return new S3FotoStorageService();
        }else{
            return new LocalFotoStorageService();
        }
    }
}
