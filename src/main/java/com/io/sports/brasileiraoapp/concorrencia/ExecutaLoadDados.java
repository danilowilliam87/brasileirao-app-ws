package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.CompeticaoRepository;
import com.io.sports.brasileiraoapp.repository.InfoPartidaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ExecutaLoadDados implements ApplicationRunner {

    private LoadDados loadDados;
    Logger LOGGER = LoggerFactory.getLogger(ExecutaLoadDados.class);
    @Autowired
    private InfoPartidaRepository repository;

    @Autowired
    private CompeticaoRepository competicaoRepository;

    public void carregarCampoId(){
        List<InfoPartida> lista = new ArrayList<>();
        lista = this.repository.findAll();
        lista.forEach(e->{
            e.setId(e.getNumeroPartida());
            this.repository.save(e);
        });
    }

    public void save() {
        try {
            Competicao brasileiro2022 = new Competicao();
            Competicao brasileiro2021 = new Competicao();
            Optional<Competicao> busca = competicaoRepository.findByNome("CAMPEONATO BRASILEIRO DE FUTEBOL - SÉRIE A - 2021");
            if(busca.isPresent()){
                brasileiro2021 = busca.get();
            }else{
                brasileiro2021.setNome("CAMPEONATO BRASILEIRO DE FUTEBOL - SÉRIE A - 2021");
            }
            int i = this.repository.findAll().size() + 1;

            for (; i <= 250; i++) {
                if(i == 245){
                    continue;
                }
                LOGGER.info("numero da partida a ser buscada : " + i);
                BotInfoPartida infoPartida = new BotInfoPartida("urlSerieA", "2022", i);
                InfoPartida partida = infoPartida.getInfoPartida();
                partida.setCompeticao(brasileiro2021);
                this.repository.save(partida);
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
        //save();
        //carregarCampoId();
    }
}
