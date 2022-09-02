package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
public class InfoPartidaThread extends Thread{
    BotInfoPartida botInfoPartida;
    List<InfoPartida> lista = new ArrayList<>();

    private String urlSerie;
    private String ano;
    private int numeroPartida;
    private int primeiroJogo;
    private int ultimoJogo;

    public InfoPartidaThread(){}
    public InfoPartidaThread(String urlSerie, String ano, int numeroPartida, int primeiroJogo, int ultimoJogo){
        this.urlSerie = urlSerie;
        this.ano = ano;
        this.numeroPartida = numeroPartida;
        this.primeiroJogo = primeiroJogo;
        this.ultimoJogo = ultimoJogo;
    }
    public InfoPartida getInfoPartida(String urlSerie, String ano, int numeroPartida){
        botInfoPartida = new BotInfoPartida(urlSerie, ano, numeroPartida);
        return botInfoPartida.getInfoPartida();
    }

    public void getJogosPorTemporada(String urlSerie, String ano, int numeroPartida,
                                     int primeiroJogo, int ultimoJogo){
        for(int i = primeiroJogo; i<= ultimoJogo; i++){
            this.lista.add(getInfoPartida(urlSerie, ano, i));
        }
    }

    @Override
    public void run() {
        getJogosPorTemporada(this.urlSerie, this.ano, this.numeroPartida, this.primeiroJogo, this.ultimoJogo);
    }
}
