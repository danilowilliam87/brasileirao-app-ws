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

    public void save(String nomeCompeticao, String url, String ano) {
        try {
            Competicao competicao = new Competicao();
            int i = 0;
            Optional<Competicao> busca = competicaoRepository.findByNome(nomeCompeticao);
            if(busca.isPresent()){
                competicao = busca.get();
            }else{
                competicao.setNome(nomeCompeticao);
                this.competicaoRepository.save(competicao);
            }

            i = this.repository.findAllByCompeticao(competicao.getId()).size() + 1;

            int limite = 380;

            if(ano.equals("2022")){
                limite = 250;
            }

            for (; i <= limite; i++) {
                if(ano.equals("2016") && i == 378){
                    continue;
                }
                LOGGER.info("numero da partida a ser buscada : " + i);
                BotInfoPartida infoPartida = new BotInfoPartida(url, ano, i);
                InfoPartida partida = infoPartida.getInfoPartida();
                partida.setCompeticao(competicao);
                this.repository.save(partida);
                LOGGER.info("Confronto : " + infoPartida.getMandante() + " x " + infoPartida.getVisitante());
                LOGGER.info("Salvo Com Sucesso!");
            }
            LOGGER.info("Total de Jogos no Banco de Dados : " + (i - 1));
            LOGGER.info("Jogos referentes a 1ª até a " + (i / 10) + "ª Rodada do" + nomeCompeticao);
        } catch (Exception e) {
            LOGGER.error("Erro : " + e.getMessage());
            save(nomeCompeticao, url, ano);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for(int i = 2019; i<= 2022; i++){
        String ano = Integer.toString(i);
        save("CAMPEONATO BRASILEIRO DE FUTEBOL - SÉRIE A - "+ano, "urlSerieA", ano);
        }
        //carregarCampoId();
    }
}
