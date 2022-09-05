package com.io.sports.brasileiraoapp;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class InfoPartidaServiceTest {

    @Autowired
    private InfoPartidasService service;

    @Test
    public void buscaCompeticao(){
        Competicao competicao = new Competicao();
        competicao = this.service.findCompeticaoById(1L);
        Assertions.assertEquals("CAMPEONATO BRASILEIRO DE FUTEBOL - SÉRIE A - 2022",competicao.getNome());
    }


    @Test
    public void buscaCompeticao2(){
        Competicao competicao = new Competicao();
        competicao = this.service.findCompeticaoByNome("CAMPEONATO BRASILEIRO DE FUTEBOL - SÉRIE A - 2022");
        Assertions.assertEquals(1L,competicao.getId());
    }

    @Test
    public void buscaCompeticao3(){
        List<Competicao> lista = new ArrayList<>();
        lista = this.service.findAllCompeticao();
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    public void buscaPartidaPorId(){
        InfoPartida partida = new InfoPartida();
        partida = this.service.findById(100L);
        Assertions.assertEquals("Coritiba - PR", partida.getTimeMandante());
        Assertions.assertEquals(1, partida.getGolsMandante());
        Assertions.assertEquals("São Paulo - SP", partida.getTimeVisitante());
        Assertions.assertEquals(1, partida.getGolsVisitante());
    }

    @Test
    public void findPartidaByNumeroAndCompeticaoTest(){
        InfoPartida partida = new InfoPartida();
        partida = this.service.findPartidaByNumeroAndCompeticao(10L, 1L);
        Assertions.assertEquals(3, partida.getGolsMandante());
        Assertions.assertEquals(0, partida.getGolsVisitante());
        Assertions.assertEquals("Coritiba - PR", partida.getTimeMandante());
        Assertions.assertEquals("Goiás - GO", partida.getTimeVisitante());
        Assertions.assertEquals(3, partida.getAutorGolMandante().size());
        Assertions.assertEquals(0, partida.getAutorGolVisitante().size());
        Assertions.assertEquals("11:00", partida.getHorarioPartida());
        Assertions.assertEquals("Domingo, 10 de Abril de 2022", partida.getDataPartida());
        Assertions.assertEquals("Couto Pereira - Curitiba - PR", partida.getLocalDaPartida());
    }

}
