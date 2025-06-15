package com.hsbc.txn.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;
import lombok.Data;
/**
 * 缓存配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "caffeine.cache.config")
public class CacheConfig {

    // 最大对象数
    private long maximumSize;
    // 根据创建时间的过期时长，秒
    private long expireAfterWriteSec;
    // 初始容量
    private int initialCapacity;
    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(expireAfterWriteSec, TimeUnit.SECONDS)
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .recordStats()
        );
        return cacheManager;
    }
}
