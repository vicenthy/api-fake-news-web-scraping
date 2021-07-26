package com.github.vicenthy.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.smallrye.mutiny.Multi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.services.intefaces.IEfarsas;

@ApplicationScoped
public class PesquisaEfarsas implements IEfarsas{


    @Override
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            String urlbase = "https://www.e-farsas.com/";
            var doc = Jsoup.connect(urlbase +"?s="+  parametro).get();
           //  Element first = doc.getElementsByClass("last").first();
           //  var totalPage = first != null ? Integer.parseInt(first.text()) : 0;
            List<ArtigoDTO> result = new ArrayList<>();
            result.addAll(getResult(doc));
           /*
             if(totalPage > 1){
                int paginate = 1;
                while(paginate <= totalPage){
                    var docPage =  Jsoup.connect(urlbase+"page/"+paginate+"?s="+ parametro).get();
                    result.addAll(getResult(docPage));
                    paginate++;
                }
                
            }
            */
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
    public Multi<List<ArtigoDTO>> verificarFakeNewsAsync(String parametro) {
        return Multi.createFrom().items(verificarFakeNews(parametro));
    }

}