package com.example.leetsgarden.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic((httpBasicConfig) ->
                        httpBasicConfig.disable()
                )
                .cors(AbstractHttpConfigurer::disable)
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/user/**").hasRole("USER")
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").hasRole("ADMIN")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/meeting-info").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/meetings").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .authenticationEntryPoint( new AuthenticationEntryPoint() {
                                                               @Override
                                                               public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                                                                   // 인증문제가 발생했을 때 이 부분을 호출한다.
                                                                   response.setStatus(401);
                                                                   response.setCharacterEncoding("utf-8");
                                                                   response.setContentType("text/html; charset=UTF-8");
                                                                   response.getWriter().write("인증되지 않은 사용자입니다.");
                                                               }



                                                           }
                                )

                                .accessDeniedHandler(new AccessDeniedHandler() {
                                                         @Override
                                                         public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                                             // 권한 문제가 발생했을 때 이 부분을 호출한다.
                                                             response.setStatus(403);
                                                             response.setCharacterEncoding("utf-8");
                                                             response.setContentType("text/html; charset=UTF-8");
                                                             response.getWriter().write("권한이 없는 사용자입니다.");
                                                         }
                                                     }
                                )
                );


        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        /*
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("*"));
//        configuration.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//
//         */
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080", "http://3.39.24.69:8080"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(false);
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setExposedHeaders(List.of("Authorization"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

