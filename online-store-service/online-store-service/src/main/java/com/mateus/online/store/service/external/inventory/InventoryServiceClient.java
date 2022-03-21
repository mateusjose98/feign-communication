package com.mateus.online.store.service.external.inventory;

import com.mateus.online.store.service.external.config.OffsetDateTimeToMillisExpander;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.time.OffsetDateTime;

public interface InventoryServiceClient {

    @RequestLine("POST /products")
    @Headers("Content-Type: application/json")
    CreateProductResponse createProduct(CreateProductRequest request);

    @RequestLine("POST /products/{productId}/buy?amount={amount}&boughtAt={boughtAt}")
    void buy(@Param("productId")String productId,
             @Param("amount") int amount,
             @Param(value = "boughtAt", expander = OffsetDateTimeToMillisExpander.class)OffsetDateTime boughtAt);

}
