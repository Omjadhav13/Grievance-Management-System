package com.grievance_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "officers")
@Data
public class Officer {

    @Id
    @Column(name = "officernum")
    private String officernum;

    private String officername;

    @Column(name = "officer_email")
    private String officerEmail;

    private String ctgnum;
    private String address;
    @Column(name = "officer_password")
    private String password;


    @Column(name = "auth_key")
    private String authKey;
}
