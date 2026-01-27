package com.grievance_management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable
public class GrievanceEmployeeView {

    @Id
    private String grvnnum;

    private String ctgnum;
    private String subject;
    private String severity;
    private String status;
    private LocalDateTime datefiled;

    public String getGrvnnum() {
        return grvnnum;
    }

    public String getCtgnum() {
        return ctgnum;
    }

    public String getSubject() {
        return subject;
    }

    public String getSeverity() {
        return severity;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDatefiled() {
        return datefiled;
    }
}
