package com.grievance_management.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/views")
public class ViewController {

    private final JdbcTemplate jdbc;

    public ViewController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/grievances/status")
    public List<Map<String, Object>> byStatus() {
        return jdbc.queryForList("SELECT * FROM vw_grievances_by_status");
    }

    @GetMapping("/grievances/category")
    public List<Map<String, Object>> byCategory() {
        return jdbc.queryForList("SELECT * FROM vw_grievances_by_category");
    }
}
