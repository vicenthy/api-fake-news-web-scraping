package com.github.vicenthy.core;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.vicenthy.dto.ArtigoDTO;
import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.IPesquisaStrategy;

@ApplicationScoped
public class PesquisaContext {
        @Inject
        StrategyConfig strategyConfig;

        public List<ArtigoDTO> search(String term, FakeNewsCheckProvider provider){
            IPesquisaStrategy strategySearch = strategyConfig.getStrategyByProvider(provider);
                if(Objects.isNull(strategySearch)){
                    return List.of();
                }else{
                    return strategySearch.verificarFakeNews(term);
                }
        }
}
