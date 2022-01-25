package com.github.vicenthy.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheManager;

import java.util.Optional;

@ApplicationScoped
public class CacheClearer {

    @Inject
    CacheManager cacheManager;

    public void clearCache(String cacheName) {
     /*   Optional<Cache> cache = cacheManager.getCache(cacheName);
        if (cache.isPresent()) {
            cache.get().invalidateAll().await().indefinitely();
        }
        */
    }
}