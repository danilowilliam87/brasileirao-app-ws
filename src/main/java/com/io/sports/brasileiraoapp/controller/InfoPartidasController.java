package com.io.sports.brasileiraoapp.controller;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/info-partidas")
public class InfoPartidasController {

    @Autowired
    private InfoPartidasService service;

    @GetMapping("/{id}")
    public InfoPartida findById(@PathVariable("id") Long id){
       return this.service.findById(id);
    }

    @GetMapping
    public InfoPartida findPartidaByNumeroAndCompeticao(@RequestParam("numero-partida") Long numero, @RequestParam("competicao-id") Long competicaoId){
        return this.service.findPartidaByNumeroAndCompeticao(numero, competicaoId);
    }

}
