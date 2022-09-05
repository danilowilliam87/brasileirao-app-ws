package com.io.sports.brasileiraoapp;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.InfoPartidaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfoPartidasRepositoryTest {

    private BotInfoPartida bot1;
    private BotInfoPartida bot2;
    private BotInfoPartida bot3;

    @Autowired
    private InfoPartidaRepository repository;

    @BeforeAll
    public void loadObject(){
        bot1 = new BotInfoPartida("urlSerieA", "2022", 1);
        bot2 = new BotInfoPartida("urlSerieA", "2022", 2);
        bot3 = new BotInfoPartida("urlSerieA", "2022", 3);
    }

    @Test
    public void saveTest(){
        InfoPartida partida1 = bot1.getInfoPartida();
        InfoPartida partida2 = bot2.getInfoPartida();
        InfoPartida partida3 = bot3.getInfoPartida();

        repository.save(partida1);
        repository.save(partida2);
        repository.save(partida3);
    }

}
