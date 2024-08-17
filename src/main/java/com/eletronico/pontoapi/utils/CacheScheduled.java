package com.eletronico.pontoapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CacheScheduled {
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    @CacheEvict("users")
    public void limparCacheUsers(){
        log.info("Executado" + LocalDateTime.now());
    }
}
