package com.example.algafoodapi.core.modelMapper;
/*
 *  @criado em: 15/07/2020 - {07:05}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
