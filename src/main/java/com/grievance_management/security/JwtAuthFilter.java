package com.grievance_management.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        log.info("➡ Incoming request: {}", path);

        if (path.equals("/api/employees/login") ||
                path.equals("/api/employees/register")) {

            log.info("✅ Public endpoint, skipping JWT");
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        log.info("🔐 Authorization header: {}", authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                String empId = jwtService.extractEmployeeId(token);
                String role  = jwtService.extractRole(token);

                // CRITICAL FIX: NORMALIZE ROLE
                String normalizedRole = role.toUpperCase();

                log.info("JWT parsed | empId={} | role(raw)={} | role(normalized)={}",
                        empId, role, normalizedRole);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                empId,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + normalizedRole))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Authentication set with authority ROLE_{}", normalizedRole);

            } catch (Exception e) {
                log.error("JWT parsing failed", e);
            }
        } else {
            log.warn("No Bearer token found");
        }

        filterChain.doFilter(request, response);
    }
}
