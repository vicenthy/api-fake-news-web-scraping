package com.github.vicenthy.services.impl;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.github.vicenthy.core.PesquisaStrategyFactory;
import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaService;
import com.github.vicenthy.services.PesquisaStrategy;

@ApplicationScoped
public class PesquisaServiceImpl implements PesquisaService{

    @Inject
    PesquisaStrategyFactory strategyConfig;

    @Override
    public List<ArtigoDTO> search(String term, FakeNewsCheckProvider provider) {
        PesquisaStrategy strategySearch = strategyConfig.getStrategyByProvider(provider);
        if (Objects.nonNull(strategySearch)) {
            return strategySearch.verificarFakeNews(term);
        }
        return List.of();
    }
    
}
