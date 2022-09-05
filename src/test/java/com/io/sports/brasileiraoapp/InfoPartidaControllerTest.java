package com.io.sports.brasileiraoapp;


import io.restassured.RestAssured;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InfoPartidaControllerTest {

    @LocalServerPort
    private int porta;

    @BeforeEach
    public void setup() {
        RestAssured.port = porta;
    }

    @Test
    public void testeFindById() {
        RestAssured
                .given()
                .port(porta)
                .when()
                .get("http://localhost:8080/info-partidas/240")
                .then()
                .body("timeMandante", IsEqual.equalTo("Coritiba - PR"));
    }

    @Test
    public void testeFindByNumeroPartidaAndCompeticao() {
        RestAssured
                .given()
                .port(porta)
                .when()
                .get("http://localhost:8080/info-partidas?numero-partida=100&competicao-id=1")
                .then()
                .body("timeMandante", IsEqual.equalTo("Coritiba - PR"))
                .body("localDaPartida", IsEqual.equalTo("Couto Pereira - Curitiba - PR"));
    }


}
