package com.github.vicenthy.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.services.intefaces.IBoatos;

@ApplicationScoped
public class PesquisaBoatosPontoOrg implements IBoatos{


        @Override
        public List<ArtigoDTO> verificarFakeNews(String parametro){
            try {

                Document doc = Jsoup.connect("https://www.boatos.org/?s=" + parametro).get();
                Elements postList = doc.getElementsByClass("featured-image");
                List<ArtigoDTO> resultados = new ArrayList<>();
                postList.stream().forEach(result -> {
                    ArtigoDTO artigoDTO = new ArtigoDTO();
                    String link = result.select("a[href]").stream().findFirst().get().attr("href");
                    String img = result.select("img").stream().findFirst().get().attr("src").split("\\?")[0];
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