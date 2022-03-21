package com.mateus.online.store.service.external.session;

import feign.QueryMap;
import feign.RequestLine;

import java.util.UUID;

public interface UserSessionClient {


    @RequestLine("GET /user-sessions/validate?sessionId={sessionId}")
    UserSessionValidatorResponse validateSession(@QueryMap ValidateSessionRequest queryMap);

    default UserSessionValidatorResponse validateSession(
            UUID sessionId){

        return validateSession(new ValidateSessionRequest(sessionId.toString()) );
    }

}
