package com.io.sports.brasileiraoapp.service;

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



}
