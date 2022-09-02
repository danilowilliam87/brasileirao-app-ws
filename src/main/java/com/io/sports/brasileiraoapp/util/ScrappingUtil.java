package com.io.sports.brasileiraoapp.util;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.dto.PartidaGoogleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrappingUtil {


    public static void main(String[] args) {
        BotInfoPartida infoPartida = new BotInfoPartida();
        infoPartida.abrirConexao("urlSerieA", "2022", 1);
        System.out.println(infoPartida.getCompeticao());
    }
}
