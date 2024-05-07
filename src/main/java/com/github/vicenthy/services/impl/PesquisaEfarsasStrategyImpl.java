package com.github.vicenthy.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.IPesquisaStrategy;
import io.quarkus.cache.CacheResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class PesquisaEfarsasStrategyImpl implements IPesquisaStrategy{

    @Override
    @CacheResult(cacheName = "e-farsas") 
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            String urlbase = "https://www.e-farsas.com/";
            var doc = Jsoup.connect(urlbase +"?s="+  parametro).get();
            List<ArtigoDTO> result = new ArrayList<>();
            result.addAll(getResult(doc));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

    private List<ArtigoDTO> getResult(Document doc) {
        var postList = doc.getElementsByClass("td-module-thumb");
       int skip = 1;
        while(skip <= 6){
            postList.remove(postList.last());
            skip++;
        }
 
        return postList.stream().parallel().map(result -> {
            ArtigoDTO artigoDTO = new ArtigoDTO();
            String link = result.select("a[href]").stream().findFirst().get().attr("href");
            String img = result.select("span").stream().findFirst().get().attr("data-img-url").split("\\?")[0];
            String titulo = result.select("a[href]").stream().findFirst().get().attr("title");
            artigoDTO.setUrlImg(img);
            artigoDTO.setTitulo(titulo);
            artigoDTO.setUrlDetalhes(link);
            return artigoDTO;

        }).collect(Collectors.toList());
    }

@Override
public FakeNewsCheckProvider provider() {
    return FakeNewsCheckProvider.EFARSAS;
}
}