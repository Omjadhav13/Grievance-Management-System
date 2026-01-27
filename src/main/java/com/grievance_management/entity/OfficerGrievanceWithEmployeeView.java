package com.grievance_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Table(name = "vw_grievance_with_employee")
@Getter
@Setter
@Immutable
public class OfficerGrievanceWithEmployeeView {

    @Id
    @Column(name = "grvnnum")
    private String grievanceNum;

    private String subject;
    private String description;
    private String status;
    private String severity;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;

    private String empnum;
    private String empname;

    @Column(name = "emp_email")
    private String empEmail;

    private String department;
}
