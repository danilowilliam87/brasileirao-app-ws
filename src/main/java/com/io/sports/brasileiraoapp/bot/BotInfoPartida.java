package com.io.sports.brasileiraoapp.bot;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.util.LeitorUrlProps;
import com.io.sports.brasileiraoapp.util.ScrappingUtil;
import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
public class BotInfoPartida {

    private InfoPartida infoPartida;
    private Competicao competicao;
    private int numeroPartida;
    private String urlSerie;
    private Document document;

    private String ano;
    private static final Logger LOGGER = LoggerFactory.getLogger(BotInfoPartida.class);

    public BotInfoPartida(String url, String ano, int numeroPartida){
        this.urlSerie = url;
        this.ano = ano;
        this.numeroPartida = numeroPartida;
        abrirConexao(this.urlSerie, this.ano, this.numeroPartida);
    }

    public BotInfoPartida(){

    }

    public void abrirConexao(String urlSerie, String ano, int numeroPartida) {
        try {
            this.urlSerie = urlSerie;
            this.numeroPartida = numeroPartida;
            String url = LeitorUrlProps.getUrl(this.urlSerie);
            String parametros = ano + "/" + this.numeroPartida;
            String urlCompleta = url.concat(parametros);
            this.document = Jsoup.connect(urlCompleta).get();
            LOGGER.info("Conexão realizada com sucesso!");
        } catch (Exception e) {
            LOGGER.error("Erro ao conectar : " + e.getMessage());
        }
    }

    public String getMandante() {
        //buscar url
        String mandante = "";
        try {
            mandante = this.document.select("div[class=col-xs-6 col-sm-3 text-center time-left]")
                    .select("h3[class=time-nome color-white]")
                    .text();
            LOGGER.info("Mandante : " + mandante);
            return mandante;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getVisitante() {
        //buscar url
        String visitante = "";
        try {
            visitante = this.document.select("div[class=col-xs-6 col-sm-3 text-center time-right]")
                    .select("h3[class=time-nome color-white]")
                    .text();
            LOGGER.info("Visitante : " + visitante);
            return visitante;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String golsTimeMandate() {
        try {
            String gols = this.document.select("div[class=col-xs-6 col-sm-3 text-center time-left]")
                    .select("div[class=time-escudo center-block m-t-10 m-b-30]")
                    .select("strong[class=time-gols block hidden-sm hidden-md hidden-lg]")
                    .text();
            LOGGER.info("Gols Mandante : " + gols);
            return gols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String golsTimeVisitante() {
        try {
            String gols = this.document.select("div[class=col-xs-6 col-sm-3 text-center time-right]")
                    .select("div[class=time-escudo center-block m-t-10 m-b-30]")
                    .select("strong[class=time-gols block hidden-sm hidden-md hidden-lg]")
                    .text();
            LOGGER.info("Gols Visitante : " + gols);
            return gols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAutorGolTimeMandante() {
        try {
            List<String> listaGols = new ArrayList<>();
            int golsMandante = Integer.parseInt(golsTimeMandate());

            if (golsMandante == 1) {
                String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                        .first()
                        .select("p[class=time-jogador]")
                        .first()
                        .text();
                listaGols.add(autorGol);
                LOGGER.info("Autor(es) de Gols na partida: " + listaGols.toString());
            } else if (golsMandante > 1) {
                for (int i = 0; i < golsMandante; i++) {
                    String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                            .first()
                            .select("p[class=time-jogador]")
                            .get(i)
                            .text();
                    listaGols.add(autorGol);
                    LOGGER.info("Autor(es) de Gols na partida: " + listaGols.toString());

                }
            }
            return listaGols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAutorGolTimeVisitante() {
        try {
            List<String> listaGols = new ArrayList<>();
            int golsVisitante = Integer.parseInt(golsTimeVisitante());

            if (golsVisitante == 1) {
                String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                        .get(1)
                        .select("p[class=time-jogador]")
                        .first()
                        .text();
                listaGols.add(autorGol);
                LOGGER.info("Autor(es) de Gols na partida: " + listaGols.toString());
            } else if (golsVisitante > 1) {
                for (int i = 0; i < golsVisitante; i++) {
                    String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                            .get(1)
                            .select("p[class=time-jogador]")
                            .get(i)
                            .text();
                    listaGols.add(autorGol);
                    LOGGER.info("Autor(es) de Gols na partida: " + listaGols.toString());
                }
            }
            return listaGols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getLocalDaPartida(){
        try {
            String local = this.document.select("div[class=col-sm-8]")
                    .select("span[class=text-2 p-r-20]")
                    .get(0)
                    .text();
            LOGGER.info("Local : " + local);
            return local;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public String getDataPartida(){
        try {
            String data = this.document.select("div[class=col-sm-8]")
                    .select("span[class=text-2 p-r-20]")
                    .get(1)
                    .text();
            LOGGER.info("Data : " + data);
            return data;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public String getHoraPartida(){
        try {
            String hora = this.document.select("div[class=col-sm-8]")
                    .select("span[class=text-2 p-r-20]")
                    .get(2)
                    .text();
            LOGGER.info("Horario: " + hora);
            return hora;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public String getCompeticao(){
        try {
            String competicao = this.document.select("header[class=section-placar-header col-md-12]")
                    .select("div[class=row]")
                    .select("div[class=col-sm-6 text-center]")
                    .text();
            //LOGGER.info("Competição : " + competicao);
            return competicao;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public InfoPartida getInfoPartida(String urlSerie, String ano, int numeroPartida){
        try {
            abrirConexao(urlSerie, ano, numeroPartida);
            infoPartida.setTimeMandante(getMandante());
            infoPartida.setTimeVisitante(getVisitante());
            infoPartida.setDataPartida(getDataPartida());
            infoPartida.setLocalDaPartida(getLocalDaPartida());
            infoPartida.setHorarioPartida(getHoraPartida());
            infoPartida.setGolsMandante(Integer.parseInt(golsTimeMandate()));
            infoPartida.setGolsVisitante(Integer.parseInt(golsTimeVisitante()));
            infoPartida.setAutorGolMandate(getAutorGolTimeMandante());
            infoPartida.setAutorGolVisitante(getAutorGolTimeVisitante());
            infoPartida.setNumeroPartida(Long.valueOf(Integer.toString(this.getNumeroPartida())));
            return infoPartida;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
