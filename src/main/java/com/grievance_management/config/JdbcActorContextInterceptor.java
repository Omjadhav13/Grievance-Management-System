package com.grievance_management.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JdbcActorContextInterceptor implements HandlerInterceptor {

    private final JdbcTemplate jdbcTemplate;

    public JdbcActorContextInterceptor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String actorId = request.getHeader("X-ACTOR-ID");
        String actorRole = request.getHeader("X-ACTOR-ROLE");

        if (actorId != null && actorRole != null) {
            jdbcTemplate.execute("SET @actor_id = '" + actorId + "'");
            jdbcTemplate.execute("SET @actor_role = '" + actorRole + "'");
        }
        return true;
    }
}
