package com.mateus.online.store.service.external.session.config;

import com.mateus.online.store.service.external.session.UserSessionClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserSessionClient userSessionClient(){
        return Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(UserSessionClient.class, "http://localhost:8082");
    }
}
