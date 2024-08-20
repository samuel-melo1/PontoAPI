package com.eletronico.pontoapi.utils.agendador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CacheScheduled {
    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.SECONDS)
    @CacheEvict(cacheNames = "users", allEntries=true)
    public void cleanerCacheUsers(){
        log.info("Executado Cache (users): " + LocalDateTime.now());
    }
    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.SECONDS)
    @CacheEvict(cacheNames = "totalElements", allEntries=true )
    public void cleanerCacheTotalElements(){
        log.info("Executado Cache (Total Elements): " + LocalDateTime.now());
    }
}
