package com.mateus.online.store.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class CreateOnlineProductRequest {

    private String name;
    private int initialStock;
}
