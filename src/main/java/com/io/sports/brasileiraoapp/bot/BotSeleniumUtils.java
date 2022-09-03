package com.io.sports.brasileiraoapp.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotSeleniumUtils {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(BotSeleniumUtils.class);
        BotSelenium botSelenium = new BotSelenium()
                .abrirPagina()
                .acessarInicioDaTabela();
        //LOGGER.info("info partida : "+botSelenium.getInfoPartida());
        //LOGGER.info("data partida : "+botSelenium.getDataPartida());
        //LOGGER.info("horario partida : "+botSelenium.getHorarioPartida());
        //LOGGER.info("numero partida : "+botSelenium.getNumeroPartida());
    }

}
