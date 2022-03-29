package com.mateus.online.store.service.external.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateus.online.store.service.external.inventory.ProductCreationFailedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class InventoryServiceCustomFeignDecoder implements ErrorDecoder {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ErrorDecoder.Default defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        boolean isInventoryServiceCreatingProduct = "InvetoryServiceClient#createProduct(CreateProductRequest)".equals(methodKey);
        if (isInventoryServiceCreatingProduct) {
            if (response.status() == HttpStatus.BAD_REQUEST.value()){
                try {
                    String s = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
                    return new ProductCreationFailedException(objectMapper.readValue(s,ErrorResponse.class ).getMessage());
                } catch (IOException e) {
                    throw new RuntimeException("Error while deserializing the response");
                }

            }

        }
        return defaultDecoder.decode(methodKey, response);

    }

    @Data
    static class ErrorResponse{
        private String message;
    }
}
