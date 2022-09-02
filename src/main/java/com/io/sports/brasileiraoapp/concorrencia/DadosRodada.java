package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.util.ScrappingUtil;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DadosRodada {
    String mandante = "", visitante = "",infoPartida = "";
    int golsMandante = 0, golsVisitante = 0;
    int numeroJogo = 0;

    Document document = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);



}
