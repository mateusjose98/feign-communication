package com.mateus.online.store.service.external.inventory;

public class ProductCreationFailedException extends  RuntimeException{

    public ProductCreationFailedException(String message) {
        super(message);
    }
}
