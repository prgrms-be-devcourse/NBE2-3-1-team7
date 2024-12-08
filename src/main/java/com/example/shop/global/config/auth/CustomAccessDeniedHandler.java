package com.example.shop.global.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        setResponse(response, HttpStatus.FORBIDDEN.value(), "권한이 부족해 요청이 거부되었습니다");
    }
    public void setResponse(HttpServletResponse response,int status,String msg) throws IOException {
        ObjectNode json = new ObjectMapper().createObjectNode();
        json.put("status",status);
        json.put("success", false);
        json.put("message", msg);
        String newResponse = new ObjectMapper().writeValueAsString(json);
        response.getWriter().write(newResponse);
        response.setStatus(status);
    }
}
