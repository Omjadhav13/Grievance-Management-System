package com.grievance_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDateTime;

@Entity
@Table(name = "vw_grievance_single")
@Immutable
@Getter @Setter
public class EmployeeSingleGrievanceView {

    @Id
    @Column(name = "grvnid")
    private Long grvnId;

    @Column(name = "grvnnum")
    private String grievanceNum;

    @Column(name = "empnum")
    private String empNum;

    private String subject;
    private String description;
    private String status;
    private String severity;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;

    @Column(name = "ctgnum")
    private String ctgnum;
}
