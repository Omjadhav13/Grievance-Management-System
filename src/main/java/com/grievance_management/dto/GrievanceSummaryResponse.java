package com.grievance_management.dto;

import java.time.LocalDateTime;

public class GrievanceSummaryResponse {

    private Long grievanceId;
    private String employeeName;
    private String category;
    private String subject;
    private String status;
    private LocalDateTime createdAt;

    // constructor + getters

    public GrievanceSummaryResponse(Long grievanceId, String employeeName, String category, String subject, String status, LocalDateTime createdAt) {
        this.grievanceId = grievanceId;
        this.employeeName = employeeName;
        this.category = category;
        this.subject = subject;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getGrievanceId() {
        return grievanceId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
