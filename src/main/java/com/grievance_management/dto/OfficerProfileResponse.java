package com.grievance_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfficerProfileResponse {

    private String officernum;
    private String officername;
    private String officerEmail;
    private String ctgnum;
    private String address;
}
