package com.example.leetsgarden.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class CustomCorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            // Preflight 요청이면 여기서 필요한 로직을 수행
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK를 반환하거나 다른 응답 코드 설정
            return;
        }
        // Preflight 요청이 아닌 다른 요청은 필터 체인으로 전달
        filterChain.doFilter(request, response);
    }
}
