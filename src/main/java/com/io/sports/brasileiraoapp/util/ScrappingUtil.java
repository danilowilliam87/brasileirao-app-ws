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
        //ScrappingUtil.retornarDadosDaPartida();
        //ScrappingUtil.buscarConfrontos();
        ScrappingUtil.jogosPrimeiroTurno();
    }

    public static PartidaGoogleDTO retornarDadosDaPartida(){
        PartidaGoogleDTO partida = new PartidaGoogleDTO();
            Document document = null;
            String urlSerieA = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_A + ANO_2022;
            String urlSerieB = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_B + ANO_2022;
        try {
            //conectando a pagina
            document = Jsoup
                       .connect(urlSerieA)
                       .get();

                String tituloDaPagina = document.title();
            for(int i = 0; i < 10; i++){
                String mandante = document.select("div[class=clearfix]")
                        .get(i).select("div[class=time pull-left]").text();

                String placar =  document.select("div[class=clearfix]")
                        .get(i).select("strong[class=partida-horario center-block]")
                        .select("span[class=bg-blue color-white label-2]")
                        .text();

                String visitante = document.select("div[class=clearfix]")
                        .get(i).select("div[class=time pull-right]").text();
                //String tituloDaPagina = document.title();
                LOGGER.info("Confronto : " + mandante +" " +placar+" " + visitante);
            }

            //clearfix
            String primeiroTimeVsistante = document.select("div[class=clearfix]")
                    .get(0).select("div[class=time pull-left]").text();



            LOGGER.info("Titulo da Pagina : " + tituloDaPagina);
            //LOGGER.info("Primeiro Time Visitante : " + primeiroTimeVsistante);
        }catch (Exception e){
           LOGGER.error("Erro : " + e.getMessage());
        }

        return partida;
    }

    public static void buscarConfrontos(){
        Document document = null;
        String urlSerieA = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_A + ANO_2022;
        String urlSerieB = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_B + ANO_2022;

        try {
            document = Jsoup.connect(urlSerieA).get();
            String home = "";
            String away = "";

            home = document.select("div[class=col-md-4 col-lg-3]")
                    .select("div[class=aside-content]")
                    .select("ul[class=list-unstyled]")
                    .select("li")
                    .first()
                    .select("div")
                    .first()
                    .select("div[class=clearfix]")
                    .select("div[class=time pull-left]")
                    .first()
                    .text();

             away = document.select("div[class=col-md-4 col-lg-3]")
                    .select("div[class=aside-content]")
                    .select("ul[class=list-unstyled]")
                    .select("li")
                    .first()
                    .select("div")
                    .first()
                    .select("div[class=clearfix]")
                    .select("div[class=time pull-right]")
                    .first()
                    .text();

            LOGGER.info("Home : " + home + " VS " + away +  " : Away");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void jogosPrimeiroTurno(){
        String mandante = "", visitante = "",infoPartida = "";
        int golsMandante = 0, golsVisitante = 0;
        int numeroJogo = 0;

        Document document = null;
        String urlSerieA = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_A + ANO_2022;
        String urlSerieB = BASE_URL_PAGINA_CBF + BASE_URL_BRASILEIRAO_SERIE_B + ANO_2022;

        try {
            for(int i = 1; i <= 190; i++){
            document = Jsoup.connect(urlSerieA + "/" + i).get();
                mandante = document.select("div[class=col-xs-6 col-sm-3 text-center time-left]")
                        .select("h3[class=time-nome color-white]")
                        .text();
                visitante = document.select("div[class=col-xs-6 col-sm-3 text-center time-right]")
                        .select("h3[class=time-nome color-white]")
                        .text();

                String golsTimeDaCasa = document.select("div[class=col-xs-6 col-sm-3 text-center time-left]")
                        .select("div[class=time-escudo center-block m-t-10 m-b-30]")
                        .select("strong[class=time-gols block hidden-sm hidden-md hidden-lg]")
                        .text();

                String golsTimeVisitante = document.select("div[class=col-xs-6 col-sm-3 text-center time-right]")
                        .select("div[class=time-escudo center-block m-t-10 m-b-30]")
                        .select("strong[class=time-gols block hidden-sm hidden-md hidden-lg]")
                        .text();

                golsMandante = Integer.parseInt(golsTimeDaCasa);
                golsVisitante = Integer.parseInt(golsTimeVisitante);

                LOGGER.info(mandante +" "+ golsMandante+ " X "+golsVisitante+ " " + visitante);
            }
        }catch (Exception e){

        }


    }
}
