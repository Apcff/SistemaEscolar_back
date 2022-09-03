package com.api.SistemaEscolar_back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class DevConfig {
 
    @Value("create")
    private String value;

    @Bean
    public boolean instanciaDB(){
        if(value.equals("create")) {
            /*this.dbService.instanciaDB();*/
            System.err.println(value);
        }
        return false;
    }
}