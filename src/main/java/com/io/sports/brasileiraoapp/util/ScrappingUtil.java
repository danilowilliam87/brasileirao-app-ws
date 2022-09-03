package com.io.sports.brasileiraoapp.util;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.dto.PartidaGoogleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrappingUtil {
    public static void main(String[] args) throws InterruptedException {
        int totalDeJogos = 0;
        try {
          for (int i = 1; i <= 5; i++) {
              int tempo = (int) (Math.ceil(Math.random() * 5000));
              Thread.sleep(tempo);
              BotInfoPartida infoPartida = new BotInfoPartida("urlSerieA", "2022", i);
              System.out.println(infoPartida.getInfoPartida());
              totalDeJogos++;
          }
      }catch (InterruptedException exception){
            System.out.println("Erro ao executar busca : "+ exception.getMessage());
      }finally {
          System.out.println("Total de Jogos retornados : " + totalDeJogos);
      }
    }
}
