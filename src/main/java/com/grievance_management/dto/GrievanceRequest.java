package com.grievance_management.dto;

public class GrievanceRequest {

    private String ctgnum;
    private String subject;
    private String description;
    private String severity;

    // getters & setters

    public String getCtgnum() {
        return ctgnum;
    }

    public void setCtgnum(String ctgnum) {
        this.ctgnum = ctgnum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
