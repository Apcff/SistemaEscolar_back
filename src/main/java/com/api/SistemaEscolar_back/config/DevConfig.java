package com.api.SistemaEscolar_back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.api.SistemaEscolar_back.services.DBService;


@Configuration
@Profile("dev")
public class DevConfig implements WebMvcConfigurer {

    @Autowired
    private DBService dbService;

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	
	
	@Bean
	public boolean instanciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false;
	}
}
