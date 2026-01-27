package com.grievance_management.service;

import com.grievance_management.dto.*;
import com.grievance_management.entity.Employee;
import com.grievance_management.repository.EmployeeRepository;
import com.grievance_management.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private static final Logger log =
            LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository,
                           JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
    }

    // ================= REGISTER =================

    public Employee registerEmployee(EmployeeRegisterRequest request) {

        Employee employee = new Employee();
        employee.setEmpnum(request.getEmpnum());
        employee.setEmpname(request.getEmpname());
        employee.setEmpEmail(request.getEmpEmail());
        employee.setPassword(request.getPassword());
        employee.setDepartment(request.getDepartment());
        employee.setRole(request.getRole());
        employee.setContactNum(request.getContactNum());
        employee.setAddress(request.getAddress());

        return employeeRepository.save(employee);
    }

    // ================= LOGIN =================

    public EmployeeLoginResponse login(EmployeeLoginRequest request) {

        Employee employee = employeeRepository
                .findByEmpEmail(request.getEmpEmail()) // ✅ correct getter
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!employee.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // 🔴 THIS IS THE ONLY REQUIRED CHANGE
        String token = jwtService.generateToken(
                employee.getEmpnum(),   // String
                employee.getRole()      // String
        );

        // ✅ BUILD YOUR EXISTING DTO
        return new EmployeeLoginResponse(
                employee.getEmpId(),        // Integer
                employee.getEmpEmail(),     // String
                employee.getRole(),         // String
                token                       // String
        );
    }

    /* ===================== VIEW PROFILE ===================== */

    public EmployeeProfileResponse getMyProfile(String empNum) {

        System.out.println("📌 SERVICE | Fetch employee profile");
        System.out.println("➡ empNum = " + empNum);

        Employee employee = employeeRepository.findByEmpnum(empNum)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("✅ SERVICE | Profile found for " + empNum);

        return new EmployeeProfileResponse(
                employee.getEmpnum(),
                employee.getEmpname(),
                employee.getEmpEmail(),
                employee.getDepartment(),
                employee.getContactNum(),
                employee.getAddress(),
                employee.getRole()
        );
    }

    /* ===================== UPdate PROFILE ===================== */

    @Transactional
    public Employee updateMyProfile(
            String empnum,
            String role,
            EmployeeUpdateRequest request) {

        log.info("🔄 SERVICE | Updating profile | empnum={} | role={}", empnum, role);

        employeeRepository.updateEmployee(
                empnum,
                request.getEmpname(),
                request.getEmpEmail(),
                request.getDepartment(),
                role,                 // role unchanged
                request.getAddress(),
                request.getContactNum(),
                empnum,               // actor_id = self
                role                  // actor_role
        );

        Employee updated = employeeRepository.findByEmpnum(empnum)
                .orElseThrow(() -> new RuntimeException("Employee not found after update"));

        log.info("✅ SERVICE | Profile updated successfully | empnum={}", empnum);

        return updated;
    }
}
