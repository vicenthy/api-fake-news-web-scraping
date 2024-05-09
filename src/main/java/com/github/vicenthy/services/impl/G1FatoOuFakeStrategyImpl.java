package com.github.vicenthy.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;


import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ApplicationScoped
public class G1FatoOuFakeStrategyImpl implements PesquisaStrategy{


   @Override
   @CacheResult(cacheName = "pesquisa-g1-fato-ou-fake") 
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            Document doc = Jsoup.connect("https://g1.globo.com/busca/?q=+%23fake+" + parametro).get();
            Elements postList = doc.getElementsByClass("widget--card");
            return postList.stream().parallel().map(result -> {
                ArtigoDTO artigoDTO = new ArtigoDTO();
                Optional<Element> findFirst = result.getElementsByClass("widget--info__media").stream().findFirst();
                if(findFirst.isPresent()){
                Element element = findFirst.get();
                String link = element.attr("href");
                String img = element.select("img").attr("src");
                String titulo = result.getElementsByClass("widget--info__title").stream().findFirst().get().text();
                artigoDTO.setUrlImg("https:" +img);
                artigoDTO.setTitulo(titulo);
                artigoDTO.setUrlDetalhes("https:"+link);
                return artigoDTO;
            }else{
                return null;
            }
            }).filter(Objects::nonNull)
            .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

@Override
public FakeNewsCheckProvider provider() {
    return FakeNewsCheckProvider.FATOOUFAKE;
}
    
}
