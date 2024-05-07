package com.github.vicenthy.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.github.vicenthy.dto.FakeNewsCheckProvider;
import com.github.vicenthy.services.IPesquisaStrategy;

@ApplicationScoped
public class StrategyConfig {
    @Inject
    Instance<IPesquisaStrategy> strategies;


        public IPesquisaStrategy getStrategyByProvider(FakeNewsCheckProvider provider) {
            return strategies.stream().filter( strategy -> provider.equals(strategy.provider())).findFirst().orElseThrow();
        }
}
