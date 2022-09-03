package com.io.sports.brasileiraoapp.service;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.CompeticaoRepository;
import com.io.sports.brasileiraoapp.repository.InfoPartidasRepository;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoPartidasService {
    @Autowired
    private InfoPartidasRepository infoPartidasRepository;
    @Autowired
    private CompeticaoRepository competicaoRepository;

    public void save(InfoPartida infoPartida){
        infoPartidasRepository.save(infoPartida);
    }
    public void save(Competicao competicao){
        competicaoRepository.save(competicao);
    }



}
