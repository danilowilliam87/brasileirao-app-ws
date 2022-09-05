package com.io.sports.brasileiraoapp;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.CompeticaoRepository;
import com.io.sports.brasileiraoapp.repository.InfoPartidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class InfoPartidaRepositoryTest {
    @Autowired
    InfoPartidaRepository repository;
    @Autowired
    CompeticaoRepository competicaoRepository;

    Logger LOGGER = LoggerFactory.getLogger(InfoPartidaRepositoryTest.class);

    @Test
    public void buscaJogosPorRodadaTest(){
        Competicao competicao = new Competicao();
        Optional<Competicao> comp = competicaoRepository.findById(1L);
        competicao = comp.isPresent() ? comp.get() : null;
        InfoPartida partida = this.repository.findById(1L).get();
        List<InfoPartida> lista = this.repository.buscarJogosPorRodada(1L,1L, 10L);
        Assertions.assertEquals(10, lista.size());
        LOGGER.info("partida : " + partida);
    }
}
