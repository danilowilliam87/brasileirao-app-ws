package com.io.sports.brasileiraoapp.controller;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info-partidas")
public class InfoPartidasController {

    @Autowired
    private InfoPartidasService service;

    @GetMapping("/{id}")
    public InfoPartida findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }
}
