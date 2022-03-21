package com.mateus.online.store.service.external;

import feign.Headers;
import feign.RequestLine;

public interface BaseClient {

    @RequestLine("GET /actuator/health")
    @Headers("X-Source: online-store-service")
    ActuatorHealthResponse health();
}
