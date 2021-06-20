package org.pbm.map.filter;

import lombok.RequiredArgsConstructor;
import org.pbm.map.domain.dto.UserView;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, header);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<UserView> responses = restTemplate.exchange("http://localhost:8080/auth/verify", HttpMethod.GET, entity, UserView.class);
            if (responses.getBody().getEmail() == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }catch (Exception ex){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);

    }

}
