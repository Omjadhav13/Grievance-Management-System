package com.grievance_management.service;

import com.grievance_management.dto.*;
import com.grievance_management.entity.Employee;
import com.grievance_management.entity.EmployeeSingleGrievanceView;
import com.grievance_management.repository.EmployeeRepository;
import com.grievance_management.repository.EmployeeSingleGrievanceViewRepository;
import com.grievance_management.security.JwtService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeSingleGrievanceViewRepository singleViewRepo;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder,
                           EmployeeSingleGrievanceViewRepository singleViewRepo) {
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.singleViewRepo = singleViewRepo;
    }

    // ================= REGISTER =================

    @Transactional
    public Employee registerEmployee(EmployeeRegisterRequest request) {

        entityManager.createNativeQuery("SET @actor_id = 'SYSTEM'").executeUpdate();
        entityManager.createNativeQuery("SET @actor_role = 'SYSTEM'").executeUpdate();

        Employee employee = new Employee();
        employee.setEmpnum(request.getEmpnum());
        employee.setEmpname(request.getEmpname());
        employee.setEmpEmail(request.getEmpEmail());
        employee.setDepartment(request.getDepartment());
        employee.setRole(request.getRole());
        employee.setContactNum(request.getContactNum());
        employee.setAddress(request.getAddress());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));

        return employeeRepository.save(employee);
    }

    // ================= LOGIN =================

    public EmployeeLoginResponse login(EmployeeLoginRequest request) {

        Employee employee = employeeRepository
                .findByEmpEmail(request.getEmpEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                employee.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                employee.getEmpnum(),
                employee.getRole()
        );

        return new EmployeeLoginResponse(
                employee.getEmpId(),
                employee.getEmpEmail(),
                employee.getRole(),
                token
        );
    }

    // ================= VIEW PROFILE =================

    public EmployeeProfileResponse getMyProfile(String empNum) {

        Employee employee = employeeRepository.findByEmpnum(empNum)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

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

    // ================= UPDATE PROFILE =================

    @Transactional
    public Employee updateMyProfile(
            String empnum,
            String role,
            EmployeeUpdateRequest request) {

        employeeRepository.updateEmployee(
                empnum,
                request.getEmpname(),
                request.getEmpEmail(),
                request.getDepartment(),
                role,
                request.getAddress(),
                request.getContactNum(),
                empnum,
                role
        );

        return employeeRepository.findByEmpnum(empnum)
                .orElseThrow(() -> new RuntimeException("Employee not found after update"));
    }

    // ================= SINGLE GRIEVANCE (EMPLOYEE) =================

    public EmployeeSingleGrievanceView getMyGrievanceByNumber(
            String grievanceNum,
            String empNum
    ) {
        return singleViewRepo
                .findByGrievanceNumAndEmpNum(grievanceNum, empNum)
                .orElseThrow(() ->
                        new RuntimeException("Grievance not found for this employee")
                );
    }
}
