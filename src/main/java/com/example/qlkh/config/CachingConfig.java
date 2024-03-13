package com.example.qlkh.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {
    private static final int INITIAL_ENTRIES = 10;
    private static final int MAX_ENTRIES = 100;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("warehouse-authority");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(INITIAL_ENTRIES)
                .maximumSize(MAX_ENTRIES));
        return cacheManager;
    }
}