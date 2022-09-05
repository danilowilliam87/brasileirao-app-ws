package com.io.sports.brasileiraoapp;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BotInfoPartidaTest {

    BotInfoPartida bot;

    @BeforeAll
    public void loadObject(){
        bot = new BotInfoPartida("urlSerieA", "2022", 1);
    }

    @Test
    public void getCompeticaoTest(){
        Assertions.assertEquals("Campeonato Brasileiro de Futebol - Série A - 2022", bot.getCompeticao());
    }

    @Test
    public void getLocalizacaoDaPartidaTest(){
        Assertions.assertEquals("Mineirão - Belo Horizonte - MG", bot.getLocalDaPartida());
    }

    @Test
    public void getDataPartidaTest(){
        Assertions.assertEquals("Domingo, 10 de Abril de 2022", bot.getDataPartida());
    }

    @Test
    public void getHorarioPartidaTest(){
        Assertions.assertEquals("16:00", bot.getHoraPartida());
    }

    @Test
    public void getTimeMandanteTest(){
        Assertions.assertEquals("Atlético Mineiro - MG", bot.getMandante());
    }

    @Test
    public void getTimeVisitanteTest(){
        Assertions.assertEquals("Internacional - RS", bot.getVisitante());
    }

    @Test
    public void getGolsMandanteTest(){
        Assertions.assertEquals("2", bot.golsTimeMandate());
    }

    @Test
    public void getGolsVisitanteTest(){
        Assertions.assertEquals("0", bot.golsTimeVisitante());
    }


    public void getAutorGolsMandanteTest(){
        //Assertions.assertTrue(bot.getAutorGolTimeMandante().get(0).contains("Hulk"));
    }

    @Test
    public void getAutorGolsVisistanteTest(){
        Assertions.assertEquals(new ArrayList<String>(),bot.getAutorGolTimeVisitante());
    }
}
