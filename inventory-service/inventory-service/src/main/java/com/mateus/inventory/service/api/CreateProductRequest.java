package com.mateus.inventory.service.api;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;
    private int initialStock;

}
