package com.github.vicenthy.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.PesquisaStrategy;

@ApplicationScoped
public class PesquisaStrategyFactory {
    @Inject
    Instance<PesquisaStrategy> strategies;


        public PesquisaStrategy getStrategyByProvider(FakeNewsCheckProvider provider) {
            return strategies.stream().filter( strategy -> provider.equals(strategy.provider())).findFirst().orElseThrow( () -> new RuntimeException("Provider não encontrado") );
        }
}
