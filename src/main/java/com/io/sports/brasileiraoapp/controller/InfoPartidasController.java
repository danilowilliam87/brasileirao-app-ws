package com.io.sports.brasileiraoapp.controller;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/info-partidas")
public class InfoPartidasController {

    @Autowired
    private InfoPartidasService service;

    @GetMapping("/{id}")
    public InfoPartida findById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @GetMapping
    public InfoPartida findPartidaByNumeroAndCompeticao(@RequestParam("numero-partida") Long numero, @RequestParam("competicao-id") Long competicaoId) {
        return this.service.findPartidaByNumeroAndCompeticao(numero, competicaoId);
    }

    @GetMapping("/jogos-rodada")
    public List<InfoPartida> findPartidasByRodada(@RequestParam("rodada") int rodada, @RequestParam("idCompeticao") Long idCompeticao) {
        return this.service.findPartidasByRodada(rodada, idCompeticao);
    }

    @GetMapping("/jogos-ano")
    public List<InfoPartida> findPartidasByAno(@RequestParam("ano") String ano,
                                               @RequestParam("time-a") String timeA,
                                               @RequestParam("time-b") String timeB) {
        return this.service.findPartidasByAno(ano, timeA, timeB);
    }

    @GetMapping("/confrontos-competicao")
    public List<InfoPartida> findPartidasByAnoAndCompeticao(@RequestParam("idCompeticao") Long idCompeticao,
                                                            @RequestParam("time-a") String timeA,
                                                            @RequestParam("time-b") String timeB) {
        return this.service.findPartidasByAnoAndCompeticao(idCompeticao, timeA, timeB);
    }

    @GetMapping("/competicao-nome")
    public Competicao findCompeticaoByNome(@RequestParam("nome") String nome) {
        return this.service.findCompeticaoByNome(nome);
    }

    @GetMapping("/competicao")
    public List<Competicao> findAllCompeticao() {
        return this.service.findAllCompeticao();
    }

    @GetMapping("/jogos-competicao")
    public List<InfoPartida> findAllByCompeticao(@RequestParam("idCompeticao") Long idCompeticao) {
        return this.service.findAllByCompeticao(idCompeticao);
    }

    @GetMapping(value = "/anos-competicao")
    public List<Integer> getAnosCompeticoes() {
        return this.service.getAnosCompeticoes();
    }

}
