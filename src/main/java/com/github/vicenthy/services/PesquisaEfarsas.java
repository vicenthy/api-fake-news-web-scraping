package com.github.vicenthy.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.services.intefaces.IEfarsas;

@ApplicationScoped
public class PesquisaEfarsas implements IEfarsas{


    @Override
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            Document doc = Jsoup.connect("https://www.e-farsas.com/?s=" + parametro).get();
            Elements postList = doc.getElementsByClass("td-module-thumb");
            List<ArtigoDTO> resultados = new ArrayList<>();
            postList.stream().forEach(result -> {
                ArtigoDTO artigoDTO = new ArtigoDTO();
                String link = result.select("a[href]").stream().findFirst().get().attr("href");
                String img = result.select("span").stream().findFirst().get().attr("data-img-url").split("\\?")[0];
                String titulo = result.select("a[href]").stream().findFirst().get().attr("title");

                artigoDTO.setUrlImg(img);
                artigoDTO.setTitulo(titulo);
                artigoDTO.setUrlDetalhes(link);

                resultados.add(artigoDTO);

            });
            return resultados;
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

}