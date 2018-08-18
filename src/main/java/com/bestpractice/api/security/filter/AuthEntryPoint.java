package com.bestpractice.api.security.filter;

import com.bestpractice.api.domain.model.Exception;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        Exception exception = new Exception();
        exception.setStatus(401);
        exception.setError("UnAuthorized");
        exception.setMessage("Incorrect authentication info");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(exception);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(json);
        } catch (java.lang.Exception ex){
            ex.printStackTrace();
        }
    }
}