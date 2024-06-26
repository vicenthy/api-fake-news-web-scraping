package com.github.vicenthy.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaStrategy;
import io.quarkus.cache.CacheResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@ApplicationScoped
public class PesquisaAosFatosStrategyImpl implements PesquisaStrategy{


    @Override
    @CacheResult(cacheName = "aosfatos") 
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            Document doc = Jsoup.connect("https://www.aosfatos.org/search/?q=" + parametro).get();
            Elements postList = doc.getElementsByClass("entry-item-card");
            return postList.stream().parallel().map(result -> {
                ArtigoDTO artigoDTO = new ArtigoDTO();
                String link = result.select("a[href]").stream().findFirst().get().attr("href");
                String attr = result.getElementsByClass("entry-item-card-image").stream().findFirst().get().attr("style");
                String img = attr.split("\'")[1]; 
                String titulo = result.getElementsByClass("entry-item-card-title").stream().findFirst().get().text();
                artigoDTO.setUrlImg(img);
                artigoDTO.setTitulo(titulo);
                artigoDTO.setUrlDetalhes("https://www.aosfatos.org"+link);
                return artigoDTO;
            }).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }
 
    

@Override
public FakeNewsCheckProvider provider() {
    return FakeNewsCheckProvider.AOSFATOS;
}
}
