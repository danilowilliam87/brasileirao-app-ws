package com.io.sports.brasileiraoapp.service;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.CompeticaoRepository;
import com.io.sports.brasileiraoapp.repository.InfoPartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoPartidasService {
    @Autowired
    private InfoPartidaRepository infoPartidasRepository;
    @Autowired
    private CompeticaoRepository competicaoRepository;

    public void save(InfoPartida infoPartida){
        this.infoPartidasRepository.save(infoPartida);
    }
    public void save(Competicao competicao){
        this.competicaoRepository.save(competicao);
    }

    public InfoPartida findPartidaById(Long id){
        return this.infoPartidasRepository.findById(id).isPresent()
                ? this.infoPartidasRepository.findById(id).get():null;
    }

    public List<InfoPartida> findAllPartidasByRodada(Integer rodada){
        int fimRodada = rodada * 10;
        int inicioRodada = fimRodada - 9;
        return null;
    }





}
