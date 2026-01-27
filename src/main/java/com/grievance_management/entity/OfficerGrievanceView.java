package com.grievance_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Table(name = "vw_grievance_with_employee")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficerGrievanceView {

    @Id
    @Column(name = "grvnnum")
    private String grievanceNum;

    private String subject;
    private String description;
    private String status;
    private String severity;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;

    @Column(name = "empnum")
    private String employeeNum;

    @Column(name = "empname")
    private String employeeName;

    @Column(name = "emp_email")
    private String employeeEmail;

    private String department;
}
