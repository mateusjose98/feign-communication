package com.mateus.inventory.service.api;


import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class InventoryServiceApi {

    private static Map<UUID, Product> productMap = new HashMap<>();

    static {
        UUID productId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        productMap.put(productId, new Product(productId, "Phone", 5, null));
    }

    @PostMapping("/products")
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest request){

        String productName = request.getName();
        if(!StringUtils.hasText(productName)) {
            throw new ProductCreationFailedException("Product name cannot be empty!");
        }

        int initialStock = request.getInitialStock();
        Product product = new Product(UUID.randomUUID(), productName, initialStock, null);
        productMap.put(product.getId(), product);

        return CreateProductResponse
                .builder()
                .productId(product.getId())
                .name(productName)
                .stock(initialStock)
                .build();
    }


    @PostMapping("/products/{productId}/buy")
    public ResponseEntity<?> buy(
            @PathVariable("productId") UUID productId,
            @RequestParam(value= "amount", defaultValue = "1") int amount,
            @RequestParam("boughtAt")OffsetDateTime boughAt
            ){
        Product product = productMap.get(productId);
        int currentStock = product.getStock();
        product.setStock(currentStock - amount);
        product.setBoughtAt(boughAt);
        return ResponseEntity.ok().build();

    }

}
