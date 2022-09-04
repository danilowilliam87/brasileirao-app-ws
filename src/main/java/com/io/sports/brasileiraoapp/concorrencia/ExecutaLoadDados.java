package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.InfoPartidasRepository;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutaLoadDados implements ApplicationRunner {

    private LoadDados loadDados;
    Logger LOGGER = LoggerFactory.getLogger(ExecutaLoadDados.class);
    @Autowired
    private InfoPartidasRepository repository;

    public void save() {
        try {
            int i = this.repository.findAll().size() + 1;
            for (; i <= 130; i++) {
                LOGGER.info("numero da partida a ser buscada : " + i);
                BotInfoPartida infoPartida = new BotInfoPartida("urlSerieA", "2022", i);
                this.repository.save(infoPartida.getInfoPartida());
                LOGGER.info("Confronto : " + infoPartida.getMandante() + " x " + infoPartida.getVisitante());
                LOGGER.info("Salvo Com Sucesso!");
            }
            LOGGER.info("Total de Jogos no Banco de Dados : " + (i - 1));
            LOGGER.info("Jogos referentes a 1ª até a " + (i / 10) + "ª Rodada do Campeonato Brasileiro 2022");
        } catch (Exception e) {
            LOGGER.error("Erro : " + e.getMessage());
            save();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        save();
    }
}
