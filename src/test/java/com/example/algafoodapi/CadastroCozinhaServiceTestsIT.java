package com.example.algafoodapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import com.example.algafoodapi.dominio.exception.CozinhaNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.service.CadastroCozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class) //Carrega Contexto de Injeção de Dependência
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaServiceTestsIT {

	@LocalServerPort
	private int port;

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.basePath("/cozinhas")
						.port(port)
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveAtribuirIdQaundoCadastrarCozinhaComDadosCorretos(){
		//Cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		//Ação
		novaCozinha = cozinhaService.salvar(novaCozinha);

		//Validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void deveFalharQaundoCadastrarCozinhaSemNome(){
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(novaCozinha));
	}

	@Test
	public void deveFalharQaundoExcluirCozinhaEmUso(){
		assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.excluir(1L));
	}

	@Test
	public void deveFalharQaundoExcluirCozinhaInexistente(){
		assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.excluir(100L));
	}

}
