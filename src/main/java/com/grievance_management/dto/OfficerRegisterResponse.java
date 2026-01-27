package com.grievance_management.dto;

public class OfficerRegisterResponse {

    private String officerEmail;
    private String authKey;

    public OfficerRegisterResponse(String officerEmail, String authKey) {
        this.officerEmail = officerEmail;
        this.authKey = authKey;
    }

    public String getOfficerEmail() {
        return officerEmail;
    }

    public String getAuthKey() {
        return authKey;
    }
}
