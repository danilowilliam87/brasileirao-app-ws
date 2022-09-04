package com.io.sports.brasileiraoapp.bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageObject {
    private WebDriver webDriver;
    private Logger LOGGER = LoggerFactory.getLogger(PageObject.class);

    public PageObject(String webDriver, String filePath) {
        System.setProperty(webDriver, filePath);
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
    }

    public PageObject(){

    }

    public void open(String url){
        LOGGER.info("Conectando a página : " + url);
        this.webDriver.get(url);
    }

    public void clickElement(String selector, String type){
        LOGGER.info("Click no elemento : " + selector+"[type="+ type + "]");
        switch (type){
            case "id": this.webDriver.findElement(By.id(selector)).click();
            break;

            case  "xpath": this.webDriver.findElement(By.xpath(selector)).click();
            break;

            default: throw new RuntimeException("Elemento não encontrado");
        }
    }

    public void findElementById(String id) {
        LOGGER.info("Busca do elemento pelo id");
        this.webDriver.findElement(By.id(id));
    }

    public WebElement findElementByXpath(String xpath) {
        LOGGER.info("Busca do elemento pelo xpath");
        return  this.webDriver.findElement(By.xpath(xpath));
    }

    public String getText(String xpath){
        return  this.webDriver.findElement(By.xpath(xpath)).getText();
    }

    public WebElement findElementByName(String name) {
        LOGGER.info("Busca do elemento pelo name");
        return this.webDriver.findElement(By.name(name));
    }

    public List<WebElement> findElementsByClass(String classe) {
        LOGGER.info("Busca do elemento(s) pela class");
        return this.webDriver.findElements(By.className(classe));
    }

    public List<WebElement> findElementByTagName(String tagName) {
        LOGGER.info("Busca do elemento(s) pela tagName");
        return this.webDriver.findElements(By.tagName(tagName));
    }

    public String getTextOfElement(String field, String findParam) {
        String text = "";
        switch (findParam) {
            case "id":
                text = this.webDriver.findElement(By.id(field)).getText();
                break;

            case "xpath":
                text = this.webDriver.findElement(By.xpath(field)).getText();
                break;

            case "tag":
                text = this.webDriver.findElement(By.tagName(field)).getText();
                break;

            case "class":
                text = this.webDriver.findElement(By.className(field)).getText();
                break;
        }
        LOGGER.info("Texto obtido do elemento : " + text);
        return text;
    }

    public void close(){
        LOGGER.info("fechamento do browser em alguns segundos");
        this.webDriver.quit();
    }


}
