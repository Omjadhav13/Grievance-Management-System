package com.grievance_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @Column(name = "grvnnum")
    private String grvnnum;

    @Column(name = "empnum")
    private String empnum;

    @Column(name = "ctgnum")
    private String ctgnum;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "severity")
    private String severity;

    @Column(name = "status")
    private String status;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;

    @Column(name = "resnnum")
    private String resnnum;

    // getters & setters

    public String getGrvnnum() {
        return grvnnum;
    }

    public void setGrvnnum(String grvnnum) {
        this.grvnnum = grvnnum;
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(LocalDateTime dateFiled) {
        this.dateFiled = dateFiled;
    }

    public String getResnnum() {
        return resnnum;
    }

    public void setResnnum(String resnnum) {
        this.resnnum = resnnum;
    }
}
