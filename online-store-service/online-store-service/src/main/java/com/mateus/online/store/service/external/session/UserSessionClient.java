package com.mateus.online.store.service.external.session;

import com.mateus.online.store.service.external.BaseClient;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@FeignClient(url = "http://localhost:8082")
public interface UserSessionClient extends BaseClient {


    @GetMapping("/user-sessions/validate?sessionId={sessionId}")
    @Headers("X-Source: online-store-service")
    UserSessionValidatorResponse validateSession(@RequestParam ValidateSessionRequest queryMap,
                                                 @RequestHeader Map<String, Object> headerMap);

    default UserSessionValidatorResponse validateSession(
            UUID sessionId){
        Map<String, Object> map = new HashMap<>();
        map.put("X-Source", "online-store-service");
        return validateSession(new ValidateSessionRequest(sessionId.toString()), map );
    }



}
