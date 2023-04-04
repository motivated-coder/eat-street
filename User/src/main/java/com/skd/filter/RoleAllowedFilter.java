package com.skd.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class RoleAllowedFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        log.info("RoleAllowedFilter is called");
        String token = extractToken(request); // extract JWT token from request header or cookie
        List<String> userRoles = extractUserRoles(token); // extract user roles from JWT token
        List<String> allowedRoles = extractAllowedRoles(request); // extract allowed roles from "@RoleAllowed" annotation

        if (allowedRoles.isEmpty() || userRoles.stream().anyMatch(allowedRoles::contains)) {
            filterChain.doFilter(request, response); // allow request to proceed
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN); // return 403 Forbidden error
        }
    }

    private String extractToken(HttpServletRequest request) {
        // implementation for extracting JWT token from request header or cookie
        String headerValue = request.getHeader(AUTHORIZATION_HEADER);
        if (headerValue != null && headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private List<String> extractUserRoles(String token) {
        // implementation for extracting user roles from JWT token
        List<String> roles = new ArrayList<>();
        if (token != null) {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
            Object rolesClaim = claims.get("roles");
            if (rolesClaim instanceof List) {
                roles.addAll((List<String>) rolesClaim);
            } else if (rolesClaim instanceof String) {
                roles.add((String) rolesClaim);
            }
        }
        log.info("Extracted User Roles are {}",roles);
        return roles;
    }
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private List<String> extractAllowedRoles(HttpServletRequest request) {
        // implementation for extracting allowed roles from "@RoleAllowed" annotation
        List<String> roles = new ArrayList<>();
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        log.info("Handler method is {}",handlerMethod);
        if (handlerMethod != null) {
            RoleAllowed roleAllowedAnnotation = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RoleAllowed.class);
            if (roleAllowedAnnotation != null) {
                roles.addAll(Arrays.asList(roleAllowedAnnotation.value()));
            }
        }
        log.info("Extracted Allowed Roles are {}",roles);
        return roles;
    }

}
