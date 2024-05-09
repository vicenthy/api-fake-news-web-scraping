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
public class PesquisaBoatosPontoOrgStrategyImpl implements PesquisaStrategy{


        @Override
        @CacheResult(cacheName = "boatos-org") 
        public List<ArtigoDTO> verificarFakeNews(String parametro){
            try {

                Document doc = Jsoup.connect("https://www.boatos.org/?s=" + parametro).get();
                Elements postList = doc.getElementsByClass("featured-image");
                return postList.stream().parallel().map(result -> {
                    ArtigoDTO artigoDTO = new ArtigoDTO();
                    String link = result.select("a[href]").stream().findFirst().get().attr("href");
                    String img = result.select("img").stream().findFirst().get().attr("src");
                    String titulo = result.select("a[href]").stream().findFirst().get().attr("title");
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
    return FakeNewsCheckProvider.BOATOS;
}
}