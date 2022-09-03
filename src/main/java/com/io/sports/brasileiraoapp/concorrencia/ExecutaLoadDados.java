package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.InfoPartidasRepository;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExecutaLoadDados {
    @Autowired
    public static InfoPartidasRepository infoPartidasRepository;

    @Autowired
    public static InfoPartidasService service;

    public static void main(String[] args) {
        String urlSerieA = "urlSerieA";
        String ano = "2022";
        int numeroPartida = 1;
        BotInfoPartida botInfoPartida = new BotInfoPartida(urlSerieA, ano, numeroPartida);
        InfoPartida infoPartida = new InfoPartida();
        infoPartida = botInfoPartida.getInfoPartida();
        service.save(infoPartida);
        System.out.println(infoPartida);
    }
}
