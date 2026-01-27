package com.grievance_management.dto;

public class OfficerRegisterRequest {

    private String officernum;
    private String officername;
    private String officerEmail;
    private String ctgnum;
    private String address;
    private String password;

    public String getOfficernum() {
        return officernum;
    }

    public void setOfficernum(String officernum) {
        this.officernum = officernum;
    }

    public String getOfficername() {
        return officername;
    }

    public void setOfficername(String officername) {
        this.officername = officername;
    }

    public String getOfficerEmail() {
        return officerEmail;
    }

    public void setOfficerEmail(String officerEmail) {
        this.officerEmail = officerEmail;
    }

    public String getCtgnum() {
        return ctgnum;
    }

    public void setCtgnum(String ctgnum) {
        this.ctgnum = ctgnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
