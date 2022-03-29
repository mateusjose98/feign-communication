package com.mateus.online.store.service.external.inventory;

import com.mateus.online.store.service.external.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081")
public interface InventoryServiceClient  extends BaseClient {

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    CreateProductResponse createProduct(CreateProductRequest request);

    @PostMapping("/products/{productId}/buy?amount={amount}&boughtAt={boughtAt}")
    void buy(@PathVariable("productId")String productId,
             @RequestParam("amount") int amount);
            // @RequestParam(value = "boughtAt" );
    //, expander = OffsetDateTimeToMillisExpander.class)OffsetDateTime boughtAt);

}
