package com.github.vicenthy.resource;



import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaService;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.vertx.ext.web.RoutingContext;



@ApplicationScoped
@javax.ws.rs.Produces(MediaType.SERVER_SENT_EVENTS)
public class PesquisaResource {


    @Inject
    PesquisaService pesquisa;




    @Route(methods = HttpMethod.GET, path = "/fatooufake/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> g1FatoOuFake(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"), FakeNewsCheckProvider.FATOOUFAKE));
        
    }


    

 
    @Route(methods = HttpMethod.GET, path = "/agencialupa/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> agencialupa(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"), FakeNewsCheckProvider.AGENCIALUPA));
          
    }


    @Route(methods = HttpMethod.GET, path = "/checamos/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> checamos(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"),FakeNewsCheckProvider.CHECAMOS));
         
    }


    @Route(methods = HttpMethod.GET, path = "/aosfatos/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> aosfatos(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"), FakeNewsCheckProvider.AOSFATOS));
       
    }

    @Route(methods = HttpMethod.GET, path = "/efarsas/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> efarsas(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"), FakeNewsCheckProvider.EFARSAS));
  
    }

    @Route(methods = HttpMethod.GET, path = "/boatos/:busca", produces = MediaType.APPLICATION_JSON)
    @Blocking
    public Multi<List<ArtigoDTO>> boatos(RoutingContext rc) {
        return Multi
        .createFrom()
        .item(pesquisa.search(rc.pathParam("busca"), FakeNewsCheckProvider.BOATOS));
     }
}

