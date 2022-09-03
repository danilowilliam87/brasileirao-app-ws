package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.bot.BotInfoPartida;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.InfoPartidasRepository;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutaLoadDados implements ApplicationRunner {

    private LoadDados loadDados;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadDados = new LoadDados();
        loadDados.loadAndSave();
    }
}
