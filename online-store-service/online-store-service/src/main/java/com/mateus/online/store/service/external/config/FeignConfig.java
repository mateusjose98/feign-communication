package com.mateus.online.store.service.external.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.mateus.online.store.service.external")
public class FeignConfig {

    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }

//   APENAS PARA CONSULTA - COM O SPRING, BASTA USAR @Component nos interceptors
//    final static Integer SEC = 1000;
//
//    @Bean
//    public InventoryServiceClient inventoryServiceClient(){
//
//
//        long timeInSecondsDoNewRequest =  1 * SEC;
//        Integer attempts = 2;
//        long maxPeriodInSeconds = 5 * SEC;
//
//
//        return Feign.builder()
//                .errorDecoder(new InventoryServiceCustomFeignDecoder())
//                .retryer(new Retryer.Default(timeInSecondsDoNewRequest, maxPeriodInSeconds,attempts))
//                .requestInterceptor(new SourceRequestInterceptor())
//                .options(new Request.Options(10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true))
//                .target(InventoryServiceClient.class, "http://localhost:8081");
//    }
}
