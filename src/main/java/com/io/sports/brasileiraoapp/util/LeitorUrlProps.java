package com.io.sports.brasileiraoapp.util;

import java.io.FileInputStream;
import java.util.Properties;

public class LeitorUrlProps {

    private static Properties getProps() {
        FileInputStream arquivo = null;
        try {
            Properties properties = new Properties();
            arquivo = new FileInputStream("src/url.properties");
            properties.load(arquivo);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrl(String nomeUrl){
        Properties properties = getProps();
        String url = "";
        switch (nomeUrl){
            case "urlSerieA" : url = properties.getProperty("props.urlSerieA"); break;
            case "urlSerieB" : url = properties.getProperty("props.urlSerieB"); break;
            default: return "opção invalida";
        }
        return url;
    }
}
