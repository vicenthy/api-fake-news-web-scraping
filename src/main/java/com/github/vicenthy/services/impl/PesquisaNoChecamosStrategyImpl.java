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
public class PesquisaNoChecamosStrategyImpl implements PesquisaStrategy{


    @Override
    @CacheResult(cacheName = "checamos") 
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        parametro = parametro.replaceAll("\\+", "%2B");
        try {
            Document doc = Jsoup.connect("https://checamos.afp.com/fact-checking-search-results?keywords=" + parametro).get();
            Elements postList = doc.getElementsByClass("card");
            return postList.stream().map(result -> {
                ArtigoDTO artigoDTO = new ArtigoDTO();
                String img = "https://checamos.afp.com" + result.select("img").stream().findFirst().get().attr("src");
                String titulo = result.getElementsByClass("card-title").stream().findFirst().get().text();
                String link = "https://checamos.afp.com" + result.select("a[href]").stream().findFirst().get().attr("href");
                artigoDTO.setUrlImg(img);
                artigoDTO.setTitulo(titulo);
                artigoDTO.setUrlDetalhes(link);
            return artigoDTO;
            }).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }


@Override
public FakeNewsCheckProvider provider() {
    return FakeNewsCheckProvider.CHECAMOS;
}
}