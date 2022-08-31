package com.io.sports.brasileiraoapp.util;

import com.io.sports.brasileiraoapp.dto.PartidaGoogleDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrappingUtil {

    /*formato da url : BASE_URL_PAGINA_CBF+BASE_URL_BRASILEIRAO_SERIE_A(OU B OU C)+ ANO_DA_COMPETIÇÃO(EX : 2019) */
    /*AS TABELAS QUE ESTÃO DISPONIVEIS SÃO DE 2012 EM DIANTE*/
    private static final String BASE_URL_PAGINA_CBF = " https://www.cbf.com.br/futebol-brasileiro/competicoes/";
    private static final String BASE_URL_BRASILEIRAO_SERIE_A = "campeonato-brasileiro-serie-a";
    private static final String BASE_URL_BRASILEIRAO_SERIE_B = "campeonato-brasileiro-serie-b";
    private static final String BASE_URL_BRASILEIRAO_SERIE_C = "campeonato-brasileiro-serie-c";
    private static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);
    private static final String ANO_2022 = "/2022";
    public static void main(String[] args) {
        ScrappingUtil.retornarDadosDaPartida();
    }

    public static PartidaGoogleDTO retornarDadosDaPartida(){
        PartidaGoogleDTO partida = new PartidaGoogleDTO();
            Document document = null;
        try {
            //conectando a pagina
            document = Jsoup
                       .connect(BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_A + ANO_2022)
                       .get();

            String tituloDaPagina = document.title();
            LOGGER.info("Titulo da Pagina : " + tituloDaPagina);
        }catch (Exception e){
           LOGGER.error("Erro : " + e.getMessage());
        }

        return partida;
    }
}
