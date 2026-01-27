package com.grievance_management.dto;

import lombok.Data;

@Data
public class OfficerLoginRequest {
    private String officerEmail;
    private String password;
    private String authKey;

    public String getOfficerEmail() {
        return officerEmail;
    }

    public void setEmail(String email) {
        this.officerEmail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }


}
