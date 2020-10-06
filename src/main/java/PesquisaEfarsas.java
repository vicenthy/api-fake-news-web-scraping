import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PesquisaEfarsas implements IPesquisa{


    @Override
    public List<ArtigoDTO> verificarFakeNews(String parametro){
        try {

            Document doc = Jsoup.connect("https://www.e-farsas.com/?s=" + parametro).get();
            Elements postList = doc.getElementsByClass("mvp-main-blog-out relative");
            List<ArtigoDTO> resultados = new ArrayList<>();
            postList.stream().forEach(result -> {

                ArtigoDTO artigoDTO = new ArtigoDTO();
                String link = result.select("a[href]").stream().findFirst().get().attr("href");
                String img = result.select("img").stream().findFirst().get().attr("src").split("\\?")[0];
                String titulo = result.select("h2").stream().findFirst().get().text();

                artigoDTO.setUrlImg(img);
                artigoDTO.setTitulo(titulo);
                artigoDTO.setUrlDetalhes(link);

                resultados.add(artigoDTO);

            });
            return resultados;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
