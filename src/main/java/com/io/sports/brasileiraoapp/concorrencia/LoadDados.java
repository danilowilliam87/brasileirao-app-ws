package com.io.sports.brasileiraoapp.concorrencia;

import com.io.sports.brasileiraoapp.domain.InfoPartida;
import com.io.sports.brasileiraoapp.service.InfoPartidasService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadDados {

    private InfoPartidaThread thread1;
    private InfoPartidaThread thread2;
    private InfoPartidaThread thread3;
    private InfoPartidaThread thread4;
    private InfoPartidaThread thread5;

    @Autowired
    private InfoPartidasService service;

    Map<String,List<InfoPartida>> mapa = new HashMap<>();

    String urlSerieA = "urlSerieA";
    String ano = "2022";

    //capturar dados do primeiro turno
    public void load(){
        thread1 = new InfoPartidaThread(urlSerieA, ano, 1, 40 );
        thread2 = new InfoPartidaThread(urlSerieA, ano, 41, 80 );
        thread3 = new InfoPartidaThread(urlSerieA, ano, 81, 120 );
        thread4 = new InfoPartidaThread(urlSerieA, ano, 121, 140 );
        thread5 = new InfoPartidaThread(urlSerieA, ano, 141, 190 );

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        mapa.put("lista1", thread1.getLista());
        mapa.put("lista2", thread2.getLista());
        mapa.put("lista3", thread3.getLista());
        mapa.put("lista4", thread4.getLista());
        mapa.put("lista5", thread5.getLista());

    }

    public void loadAndSave(){
        try {

            thread1 = new InfoPartidaThread(urlSerieA, ano, 1, 125 );
            //thread2 = new InfoPartidaThread(urlSerieA, ano, 126, 240 );

            thread1.start();
            //thread2.start();

            //mapa.put("lista1", thread1.getLista());
            //mapa.put("lista2", thread2.getLista());


            /*
            for(InfoPartida ip : mapa.get("lista2")){
                service.save(ip.getCompeticao());
                service.save(ip);
            }*/
        }catch (Exception exception){

        }
    }

    public void saveData(){
        for(InfoPartida ip : mapa.get("lista1")){
            service.save(ip);
        }

        for(InfoPartida ip : mapa.get("lista2")){
            service.save(ip);
        }

        for(InfoPartida ip : mapa.get("lista3")){
            service.save(ip);
        }

        for(InfoPartida ip : mapa.get("lista4")){
            service.save(ip);
        }

        for(InfoPartida ip : mapa.get("lista5")){
            service.save(ip);
        }
    }
}
