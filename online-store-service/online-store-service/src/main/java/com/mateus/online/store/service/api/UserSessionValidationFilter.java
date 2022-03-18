package com.mateus.online.store.service.api;

import com.mateus.online.store.service.external.session.UserSessionClient;
import com.mateus.online.store.service.external.session.UserSessionValidatorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserSessionValidationFilter implements Filter {

    private final UserSessionClient userSessionClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String sessionIdHeader = req.getHeader("X-Session-Id");
        if(sessionIdHeader == null){
            res.sendError(HttpStatus.FORBIDDEN.value());
        } else {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("sessionId", sessionIdHeader);
            queryMap.put("anotherId", UUID.randomUUID().toString());
            UserSessionValidatorResponse userSessionValidatorResponse = userSessionClient.validateSession(queryMap);
            if (!userSessionValidatorResponse.isValid()){
                res.sendError(HttpStatus.FORBIDDEN.value());
            } else {

                chain.doFilter(request, response);
            }

        }


    }
}
