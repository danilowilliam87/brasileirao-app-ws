package com.io.sports.brasileiraoapp;

import com.io.sports.brasileiraoapp.util.WebScrappingUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebScrappingUtilsTest {

    @Test
    public void obterTituloDaPaginaTest(){
        WebScrappingUtils utils = new WebScrappingUtils("https://www.globo.com/");
        Assertions.assertTrue(utils.getTitlePage()
                .contains("Absolutamente tudo sobre notícias"));
    }

    @Test
    public void obterTimeMandante(){
        WebScrappingUtils utils = new WebScrappingUtils("https://www.cbf.com.br/futebol-brasileiro/competicoes/campeonato-brasileiro-serie-a/2022");
        Assertions.assertEquals("JUV", utils.getHomeTeam());
    }

}
