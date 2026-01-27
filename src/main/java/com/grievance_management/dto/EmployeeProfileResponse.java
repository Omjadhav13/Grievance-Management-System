package com.grievance_management.dto;

public class EmployeeProfileResponse {

    private String empnum;
    private String empname;
    private String empEmail;
    private String department;
    private String contactNum;
    private String address;
    private String role;

    public EmployeeProfileResponse(
            String empnum,
            String empname,
            String empEmail,
            String department,
            String contactNum,
            String address,
            String role) {

        this.empnum = empnum;
        this.empname = empname;
        this.empEmail = empEmail;
        this.department = department;
        this.contactNum = contactNum;
        this.address = address;
        this.role = role;
    }

    public String getEmpnum() {
        return empnum;
    }

    public String getEmpname() {
        return empname;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getDepartment() {
        return department;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}
