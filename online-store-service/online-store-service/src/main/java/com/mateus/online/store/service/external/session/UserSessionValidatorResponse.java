package com.mateus.online.store.service.external.session;

import lombok.Data;

import java.util.UUID;

@Data
public class UserSessionValidatorResponse {

    private boolean valid;
    private UUID sessionId;
}
