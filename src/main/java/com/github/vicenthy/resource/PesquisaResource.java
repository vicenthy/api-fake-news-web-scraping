package com.github.vicenthy.resource;



import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.services.intefaces.IBoatos;
import com.github.vicenthy.services.intefaces.IChecamos;
import com.github.vicenthy.services.intefaces.IEfarsas;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;



@ApplicationScoped
public class PesquisaResource {


    @Inject
    IBoatos boatos;
    @Inject
    IChecamos checamos;
    @Inject
    IEfarsas efarsas;


    
    @Route(methods = HttpMethod.GET, path = "/checamos/:busca", produces = MediaType.APPLICATION_JSON)
    public Uni<List<ArtigoDTO>> checamos(RoutingContext rc) {
        return Uni
        .createFrom()
        .item(checamos.verificarFakeNews(rc.pathParam("busca")));
        
    }

    @Route(methods = HttpMethod.GET, path = "/efarsas/:busca", produces = MediaType.APPLICATION_JSON)
    public Uni<List<ArtigoDTO>> efarsas(RoutingContext rc) {
        return Uni
        .createFrom()
        .item(efarsas.verificarFakeNews(rc.pathParam("busca")));  
    }


    @Route(methods = HttpMethod.GET, path = "/boatos/:busca", produces = MediaType.APPLICATION_JSON)
    public Uni<List<ArtigoDTO>> boatos(RoutingContext rc) {
        return Uni
        .createFrom()
        .item(boatos.verificarFakeNews(rc.pathParam("busca")));   
    }

    //@Path("/boatos/{busca}")
   
    //@Path("/efarsas/{busca}")
   

}

