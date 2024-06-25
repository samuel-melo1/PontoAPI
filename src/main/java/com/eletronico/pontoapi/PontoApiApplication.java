package com.eletronico.pontoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(info = @Info(title = "Ponto API", version = "1.0",description = "Eletronic Management People System"))
@SpringBootApplication
@EnableCaching
public class PontoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PontoApiApplication.class, args);
    }

}
