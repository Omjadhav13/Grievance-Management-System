package com.grievance_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empId")
    private Integer empId;

    @Column(name = "empnum", length = 4, nullable = false)
    private String empnum;

    @Column(name = "empname", nullable = false)
    private String empname;

    @Column(name = "emp_email", nullable = false, unique = true)
    private String empEmail;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact_num", nullable = false, unique = true)
    private String contactNum;

    @Column(name = "emp_password", nullable = false)
    private String password;


    /* ===== getters & setters ===== */

    public Integer getEmpId() {
        return empId;
    }

    public String getEmpnum() { return empnum; }
    public void setEmpnum(String empnum) { this.empnum = empnum; }

    public String getEmpname() { return empname; }
    public void setEmpname(String empname) { this.empname = empname; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactNum() { return contactNum; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
