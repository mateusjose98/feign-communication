package com.mateus.online.store.service.external.session;

import com.mateus.online.store.service.external.BaseClient;
import feign.HeaderMap;
import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface UserSessionClient extends BaseClient {


    @RequestLine("GET /user-sessions/validate?sessionId={sessionId}")
    @Headers("X-Source: online-store-service")
    UserSessionValidatorResponse validateSession(@QueryMap ValidateSessionRequest queryMap,
                                                 @HeaderMap Map<String, Object> headerMap);

    default UserSessionValidatorResponse validateSession(
            UUID sessionId){
        Map<String, Object> map = new HashMap<>();
        map.put("X-Source", "online-store-service");
        return validateSession(new ValidateSessionRequest(sessionId.toString()), map );
    }



}
