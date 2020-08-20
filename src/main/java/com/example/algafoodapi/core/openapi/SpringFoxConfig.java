package com.example.algafoodapi.core.openapi;
/*
 *  @criado em: 18/08/2020 - {14:31}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.exceptionhandler.Problem;
import com.example.algafoodapi.core.openapi.model.PageableModelOpenAPI;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket(){
        TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                        .apis(Predicates.and(
                                    RequestHandlerSelectors.basePackage("com.example.algafoodapi.api")
//                                    RequestHandlerSelectors.basePackage("com.example.algafoodapi.outro")
                                ))
                          .paths(PathSelectors.any())
//                        .paths(PathSelectors.ant("/restaurantes/*"))
                        .build()
                    .useDefaultResponseMessages(false)
                    .globalResponseMessage(RequestMethod.GET, globalGetResponseMessage())
                    .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                    .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                    .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                    .additionalModels(typeResolver.resolve(Problem.class))
                    .directModelSubstitute(Pageable.class, PageableModelOpenAPI.class)
                    .apiInfo(apiInfo())
                    .tags(new Tag("Cidades","Gerencia as Cidades"),
                          new Tag("Grupos","Gerencia os Grupos de Usuários"));

    }

    private List<ResponseMessage> globalGetResponseMessage(){
        return Arrays.asList(
                new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                    .message("Erro Interno do servidor")
                    .build(),

                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisição inválida (erro do cliente)")
                        .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                        .message("Erro interno no servidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                        .message("Requisição recusada porque o corpo está em um formato não suportado")
                        .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                        .message("Requisição inválida (erro do cliente)")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseModel(new ModelRef("Problema")) //Esse nome é o @ApiModel("Problema") da classe Problem
                        .message("Erro interno no servidor")
                        .build()
        );
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API Aberta para Clientes e Restaurantes")
                .version("1.0")
                .contact(new Contact("mrdsolucoesemti","www.mrdsolucoesemti.com.br","carlosmedeiroslima1981@gmail.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
