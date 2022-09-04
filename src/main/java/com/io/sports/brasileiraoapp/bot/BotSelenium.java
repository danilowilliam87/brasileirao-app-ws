package com.io.sports.brasileiraoapp.bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotSelenium extends PageObject{

    Logger LOGGER = LoggerFactory.getLogger(BotSelenium.class);

    public BotSelenium(String drive, String path){
        super(drive, path);
    }

    public void abrirPagina(String url){
          this.open(url);
    }



    public void acessarInicioDaTabela(){
        String seletor = "//*[@id=\\\"menu-panel\\\"]/article/div[1]/div/div/section[1]/div[2]/aside/div/div[25]/header/div[2]/i";
        String tipo = "xpath";
        this.clickElement(seletor, tipo);
    }

    public String getText(String xpath) {
        return this.getText(xpath);
    }

    public boolean encontarElemento(String xpath){
        return this.findElementByXpath(xpath)!= null;
    }

    public String getTimeMandante(String selector, String type){
        LOGGER.info("Time Mandante : " + this.getTextOfElement(selector, type));
        return this.getTextOfElement(selector, type);
    }

    public String getTimeVisitante(String selector, String type){
        LOGGER.info("Time Visitante : " + this.getTextOfElement(selector, type));
        return this.getTextOfElement(selector, type);
    }

    public String getGolsTimeMandante(String selector, String type){
        return this.getTextOfElement(selector, type);
    }

    public String getGolsTimeVisitante(String selector, String type){
        return this.getTextOfElement(selector, type);
    }

    public String getMarcadorGols(String selector, String type){
        return this.getTextOfElement(selector, type);
    }


    public String getInfoPartida(){
        //String info = this.webDriver.findElement(By.xpath("/html/body/div[1]/main/article/div[1]/div/div/section[1]/div[2]/aside/div/div[25]/div/ul/li[1]/div/span[1]")).getText();
        return "";
    }

    public String getDataPartida(){
        String info = getInfoPartida();
        return info.substring(0,16);
    }

    public String getHorarioPartida(){
        String info = getInfoPartida();
        return info.substring(16,21);
    }

    public String getNumeroPartida(){
        String info = getInfoPartida();
        return info.substring(30,33);
    }

    public String golsVisitante(){
        return "";
    }

    public String golsMandante(){

        return "";
    }
}
