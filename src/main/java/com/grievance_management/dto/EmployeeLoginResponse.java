package com.grievance_management.dto;

public class EmployeeLoginResponse {

    private Integer empId;
    private String empEmail;
    private String role;
    private String token;

    public EmployeeLoginResponse(
            Integer empId,
            String empEmail,
            String role,
            String token) {

        this.empId = empId;
        this.empEmail = empEmail;
        this.role = role;
        this.token = token;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
