package com.io.sports.brasileiraoapp.bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BotSelenium {

    private WebDriver webDriver;



    public BotSelenium(){
        System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
        webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
    }

    public BotSelenium abrirPagina(){
        this.webDriver.get("https://www.cbf.com.br/futebol-brasileiro/competicoes/campeonato-brasileiro-serie-a/");
        return this;
    }

    public BotSelenium acessarInicioDaTabela(){
        this.webDriver.findElement(By.xpath("//*[@id=\"menu-panel\"]/article/div[1]/div/div/section[1]/div[2]/aside/div/div[25]/header/div[2]/i")).click();
        return this;
    }


    public String getInfoPartida(){
        String info = this.webDriver.findElement(By.xpath("/html/body/div[1]/main/article/div[1]/div/div/section[1]/div[2]/aside/div/div[25]/div/ul/li[1]/div/span[1]")).getText();
        return info;
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
}
