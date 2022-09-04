package com.io.sports.brasileiraoapp.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotSeleniumUtils {
    public static void main(String[] args) {
        try {
            BotSelenium bot = new BotSelenium("webdriver.chrome.driver", "./chromedriver.exe");
            bot.open("https://www.cbf.com.br/futebol-brasileiro/competicoes/campeonato-brasileiro-serie-a/2022/4");

            String timeMandante = bot.getTimeMandante("/html/body/div[1]/main/article/section[1]/div/div/div/div/div/div[1]/h3", "xpath");
            String timeVisitante = bot.getTimeVisitante("/html/body/div[1]/main/article/section[1]/div/div/div/div/div/div[5]/h3", "xpath");
            String totalGolsTimeMandante = bot.getGolsTimeMandante("/html/body/div[1]/main/article/section[1]/div/div/div/div/div/div[1]/div[1]/strong", "xpath");
            String totalGoslTimeVisitante = bot.getGolsTimeVisitante("/html/body/div[1]/main/article/section[1]/div/div/div/div/div/div[5]/div[1]/strong","xpath");
            String autoresDosgols;

            System.out.println(timeMandante + " " + totalGolsTimeMandante + " X " + totalGoslTimeVisitante + " " + timeVisitante);
            System.out.println("jogador : "+bot.getMarcadorGols("/html/body/div[1]/main/article/section[1]/div/div/div/div/div/div[1]/div[2]/p[1]","xpath"));
            //Thread.sleep(5000);
            //bot.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
