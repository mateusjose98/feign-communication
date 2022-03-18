package com.mateus.online.store.service.external.session;

import feign.QueryMap;
import feign.RequestLine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface UserSessionClient {


    @RequestLine("GET /user-sessions/validate?sessionId={sessionId}")
    UserSessionValidatorResponse validateSession(@QueryMap Map<String, Object> queryMap);

    default UserSessionValidatorResponse validateSession(
            UUID sessionId){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", sessionId.toString());
        return validateSession(sessionId);
    }

}
