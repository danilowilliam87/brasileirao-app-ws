package com.io.sports.brasileiraoapp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebScrappingUtils {

    private Document document = null;
    private String url;
    private Logger LOGGER = LoggerFactory.getLogger(WebScrappingUtils.class);

    public WebScrappingUtils(String url) {
        this.url = url;
    }

    public String getTitlePage(){
        try {
            document = Jsoup.connect(this.url).get();
            LOGGER.info("conexão to url : " + url);
        }catch (Exception e){
            LOGGER.error("Erro : " + e.getMessage());
        }
        return document.title();
    }

    public String getGuesTeam(){
        return "";
    }

    public String getHomeTeam(){
        String timeMandante = "";
        try {
            document = Jsoup.connect(this.url).get();
            LOGGER.info("conexão to url : " + url);
            timeMandante = document
                    .select("div[class=clearfix]")
                    .select("div[class=time pull-left]")
                    .first().text();
        }catch (Exception e){
            LOGGER.error("Erro : " + e.getMessage());
        }
        return timeMandante;
    }
}
