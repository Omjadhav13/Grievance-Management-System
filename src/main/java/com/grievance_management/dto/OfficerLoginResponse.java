package com.grievance_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class OfficerLoginResponse {
    private String token;
    private String officerEmail;

    public OfficerLoginResponse(String token, String officerEmail) {
        this.token = token;
        this.officerEmail = officerEmail;
    }
}
