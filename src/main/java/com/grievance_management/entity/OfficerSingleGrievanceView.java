package com.grievance_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "vw_grievance_single")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class OfficerSingleGrievanceView {

    @Id
    @Column(name = "grvnid")
    private Integer grievanceId;

    @Column(name = "grvnnum")
    private String grievanceNum;

    @Column(name = "empnum")
    private String empNum;

    @Column(name = "ctgnum")
    private String categoryNum;

    private String subject;
    private String description;
    private String status;
    private String severity;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;
}

