package com.example.algafoodapi;

import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import com.example.algafoodapi.dominio.service.CadastroCozinhaService;
import com.example.algafoodapi.util.DataBaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class) //Carrega Contexto de Injeção de Dependência
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" }) //Para Spring Boot 2
class CadastroCozinhaServiceTestsIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DataBaseCleaner dataBaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

//	@Autowired
//	private CadastroCozinhaService cozinhaService;

	//@Before //Funciona Para JUnit 4
	@BeforeEach //Esse Funciona para o JUnit Jupiter
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath= "/cozinhas";

		dataBaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha(){
		RestAssured.given()
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter2Cozinhas_QuandoConsultarNoBancoDeDados(){
		RestAssured.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", hasSize(2))
					.body("nome", Matchers.hasItems("Tailandesa", "Americana"));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha(){
		RestAssured.given()
						.body("{ \"nome\": \"Chinesa\" }")
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
					.when()
						.post()
					.then()
						.statusCode(HttpStatus.CREATED.value());
	}

	/*
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
	*/

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente(){
		RestAssured.given()
						.pathParam("cozinhaId",2)
						.accept(ContentType.JSON)
					.when()
						.get("/{cozinhaId}")
					.then()
						.statusCode(HttpStatus.OK.value())
						.body("nome",equalTo("Americana"));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente(){
		RestAssured.given()
						.pathParam("cozinhaId",100)
						.accept(ContentType.JSON)
					.when()
						.get("/{cozinhaId}")
					.then()
					.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados(){
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);

	}


}
