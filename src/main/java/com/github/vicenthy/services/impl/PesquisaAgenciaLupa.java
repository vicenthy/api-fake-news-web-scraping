package com.github.vicenthy.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.services.intefaces.IAgenciaLupa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ApplicationScoped
public class PesquisaAgenciaLupa implements IAgenciaLupa{


    @Override
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            Document doc = Jsoup.connect("https://piaui.folha.uol.com.br/lupa/tag/verificamos?s=" + parametro).get();
            Elements postList = doc.getElementsByClass("size-2");
            return postList.stream().parallel().map(result -> {
                ArtigoDTO artigoDTO = new ArtigoDTO();
                Element element = result.select("a[href]").stream().findFirst().get();
                String link = element.attr("href");
                String img = element.attr("data-bg");
                String titulo = result.getElementsByClass("bloco-title").stream().findFirst().get().text();
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
    
}