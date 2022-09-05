package com.io.sports.brasileiraoapp.service;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.exception.RecursoInexistenteException;
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

    public InfoPartida findById(Long id){
        return this.infoPartidasRepository
                .findById(id)
                .orElseThrow(()-> new RecursoInexistenteException("não foi encontrado :( "));
    }

    public InfoPartida findPartidaByNumeroAndCompeticao(Long numero, Long competicaoId){
        return this.infoPartidasRepository
                .findPartidaByNumeroAndCompeticao(numero, competicaoId)
                .orElseThrow(() -> new RecursoInexistenteException("não foi encontrado recurso com  :( "));
    }

    public List<InfoPartida> findPartidasByRodada(int rodada, Long idCompeticao){
        Long ultimoJogo = (long) rodada * 10;
        Long primeiroJogo = (long) rodada - 9;
        return this.infoPartidasRepository.buscarJogosPorRodada(idCompeticao, primeiroJogo, ultimoJogo);
    }

    public List<InfoPartida> findPartidasByAno(String ano, String timeA, String timeB){
        return this.infoPartidasRepository.findConfrontos(ano, timeA, timeB);
    }

    public List<InfoPartida> findPartidasByAnoAndCompeticao(Long idCompeticao, String timeA, String timeB){
        return this.infoPartidasRepository.findConfrontosByCompeticao(idCompeticao, timeA, timeB);
    }

    public List<Competicao> findAllCompeticao(){
        return this.competicaoRepository.findAll();
    }

    public Competicao findCompeticaoById(Long id){
        return this.competicaoRepository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException("recurso inexistente :("));
    }

    public Competicao findCompeticaoByNome(String nome){
        return this.competicaoRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoInexistenteException("recurso inexiste :("));
    }
}
