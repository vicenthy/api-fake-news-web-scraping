package com.github.vicenthy.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.github.vicenthy.core.PesquisaContext;
import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaService;

@ApplicationScoped
public class PesquisaServiceImpl implements PesquisaService{

    @Inject
    PesquisaContext context;

    @Override
    public List<ArtigoDTO> search(String term, FakeNewsCheckProvider provider) {
        return context.search(term, provider);
    }
    
}
