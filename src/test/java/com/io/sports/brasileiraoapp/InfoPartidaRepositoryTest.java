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
    /*
    * "Corinthians - SP"
    * "Internacional - RS"
    * */
    @Test
    public void buscarConfrontos(){
        List<InfoPartida> lista = this.repository.findConfrontos("2022","Internacional - RS", "Corinthians - SP");
        Assertions.assertEquals(2, lista.size());
        Assertions.assertEquals(2, lista.get(0).getGolsMandante());
        Assertions.assertEquals(2, lista.get(1).getGolsVisitante());
    }
    @Test
    public void buscarConfrontos2(){
        List<InfoPartida> lista = this.repository.findConfrontosByCompeticao(1L,"Botafogo - RJ", "Corinthians - SP");
        Assertions.assertEquals(2, lista.size());
        Assertions.assertEquals(3, lista.get(0).getGolsVisitante());
        Assertions.assertEquals(0, lista.get(1).getGolsVisitante());
    }


    /*
    @Test
    public void buscarConfronto3(){
        InfoPartida partida = new InfoPartida();
        partida = this.repository.findPartidabyNumeroAndCompeticao(11L, 1L);
        Assertions.assertEquals("América Fc Saf - MG", partida.getTimeMandante());
        Assertions.assertEquals("Juventude - RS", partida.getTimeVisitante());
        Assertions.assertEquals(4, partida.getGolsMandante());
        Assertions.assertEquals(1, partida.getGolsVisitante());
    }

     */
}
