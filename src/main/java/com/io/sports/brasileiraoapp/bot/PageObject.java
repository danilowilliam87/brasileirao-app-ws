package com.io.sports.brasileiraoapp.bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PageObject {
    private WebDriver webDriver;

    public PageObject(String webDriver, String filePath) {
        System.setProperty(webDriver, filePath);
        this.webDriver = new ChromeDriver();
    }

    public PageObject findElementById(String id) {
        this.webDriver.findElement(By.id(id));
        return this;
    }

    public PageObject findElementByXpath(String xpath) {
        this.webDriver.findElement(By.xpath(xpath));
        return this;
    }

    public PageObject findElementByName(String name) {
        this.webDriver.findElement(By.name(name));
        return this;
    }

    public List<WebElement> findElementsByClass(String classe) {
        return this.webDriver.findElements(By.className(classe));
    }

    public List<WebElement> findElementByTagName(String tagName) {
        return this.webDriver.findElements(By.tagName(tagName));
    }

    public String getTextOfElement(String field, String findParam) {
        String text = "";
        switch (findParam) {
            case "id":
                text = this.webDriver.findElement(By.id(findParam)).getText();
                break;

            case "xpath":
                text = this.webDriver.findElement(By.xpath(findParam)).getText();
                break;

            case "tag":
                text = this.webDriver.findElement(By.tagName(findParam)).getText();
                break;
        }

        return text;
    }


}
