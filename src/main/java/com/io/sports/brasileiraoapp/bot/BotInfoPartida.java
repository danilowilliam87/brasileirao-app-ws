package com.io.sports.brasileiraoapp.bot;

import com.io.sports.brasileiraoapp.domain.Competicao;
import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.repository.InfoPartidaRepository;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import com.io.sports.brasileiraoapp.util.LeitorUrlProps;
import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Autowired
    private InfoPartidasService service;
    @Autowired
    private InfoPartidaRepository repository;
    private String ano;
    private static final Logger LOGGER = LoggerFactory.getLogger(BotInfoPartida.class);

    public BotInfoPartida(String url, String ano, int numeroPartida){
        this.urlSerie = url;
        this.ano = ano;
        this.numeroPartida = numeroPartida;

    }

    public BotInfoPartida(){

    }

    public boolean abrirConexao(String urlSerie, String ano, int numeroPartida) {
        try {
            this.urlSerie = urlSerie;
            this.numeroPartida = numeroPartida;
            String url = LeitorUrlProps.getUrl(this.urlSerie);
            String parametros = ano + "/" + this.numeroPartida;
            String urlCompleta = url.concat(parametros);
            this.document = Jsoup.connect(urlCompleta).get();
            LOGGER.info("Conexão realizada com sucesso!");
            return true;
        } catch (Exception e) {
            LOGGER.error("Erro ao conectar : " + e.getMessage());
        }
        return false;
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

    public Set<String> getAutorGolTimeMandante() {
        try {
            Set<String> listaGols = new HashSet<>();
            int golsMandante = Integer.parseInt(golsTimeMandate());

            if (golsMandante == 1) {
                String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                        .select("p")
                        .first()
                        .text();
                LOGGER.info("Autor de Gols time Mandante: " + autorGol);
                listaGols.add(autorGol);
            } else if (golsMandante > 1) {
                for (int i = 0; i < golsMandante; i++) {
                    String autorGol = this.document.select("div[class=hidden-sm hidden-md hidden-lg m-t-20]")
                            .select("p")
                            .get(i)
                            .text();
                    LOGGER.info("Autor de Gols time Mandante: " + autorGol);
                    listaGols.add(autorGol);
                }
            }
            return listaGols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getAutorGolTimeVisitante() {
        try {
            Set<String> listaGols = new HashSet<>();
            int golsVisitante = Integer.parseInt(golsTimeVisitante());

            if (golsVisitante == 1) {
                String autorGol = this.document.select("div[class=col-xs-3 col-sm-3 text-right hidden-xs]")
                        .select("p")
                        .first()
                        .text();
                LOGGER.info("Autor de Gols time Visitante: " + autorGol);
                listaGols.add(autorGol);

            } else if (golsVisitante > 1) {
                for (int i = 0; i < golsVisitante; i++) {
                    String autorGol = this.document.select("div[class=col-xs-3 col-sm-3 text-right hidden-xs]")
                            .select("p")
                            .get(i)
                            .text();
                    LOGGER.info("Autor de Gols time Visitante: " + autorGol);
                    listaGols.add(autorGol);
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

    public InfoPartida getInfoPartida(){
        try {
            if(!abrirConexao(this.urlSerie, this.ano, this.numeroPartida)){
               throw new RuntimeException("Erro ao abrir conexão com a página alvo!");
            }else {
                //this.competicao = new Competicao();
                //this.competicao.setNome(getCompeticao());
                this.infoPartida = new InfoPartida();
                this.infoPartida.setTimeMandante(getMandante());
                this.infoPartida.setTimeVisitante(getVisitante());
                this.infoPartida.setDataPartida(getDataPartida());
                this.infoPartida.setLocalDaPartida(getLocalDaPartida());
                this.infoPartida.setHorarioPartida(getHoraPartida());
                this.infoPartida.setGolsMandante(Integer.parseInt(golsTimeMandate()));
                this.infoPartida.setGolsVisitante(Integer.parseInt(golsTimeVisitante()));
                this.infoPartida.setAutorGolMandante(getAutorGolTimeMandante());
                this.infoPartida.setAutorGolVisitante(getAutorGolTimeVisitante());
                this.infoPartida.setNumeroPartida(Long.valueOf(Integer.toString(this.getNumeroPartida())));
                //this.infoPartida.setCompeticao(competicao);
                return this.infoPartida;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
