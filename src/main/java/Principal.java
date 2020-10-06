import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.UrlEscapers;


import static spark.Spark.*;

import java.util.List;

public class Principal {

    public static void main(String[] args) {
        PesquisaNoChecamos pesquisaNoChecamos = new PesquisaNoChecamos();
        PesquisaBoatosPontoOrg pesquisaBoatosPontoOrg = new PesquisaBoatosPontoOrg();
        PesquisaEfarsas pesquisaEfarsas = new PesquisaEfarsas();
        get("/checamos/:busca", (request, response) -> {
            return pesquisar(pesquisaNoChecamos, request, response);
        });
        get("/boatos/:busca", (request, response) -> {
            return pesquisar(pesquisaBoatosPontoOrg, request, response);
        });
        get("/efarsas/:busca", (request, response) -> {
            return pesquisar(pesquisaEfarsas, request, response);
        });


    }



    private static String pesquisar(IPesquisa pesquisa, spark.Request request, spark.Response response) throws JsonProcessingException {
        response.type("application/json");
        String parametro = UrlEscapers.urlFormParameterEscaper().escape(request.params("busca"));
        if (parametro != null) {
            List<ArtigoDTO> list = pesquisa.verificarFakeNews(parametro);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        }
        return null;
    }

}
