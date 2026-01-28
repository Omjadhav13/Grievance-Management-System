package com.grievance_management.controller;

import com.grievance_management.dto.*;
import com.grievance_management.entity.Employee;
import com.grievance_management.entity.EmployeeSingleGrievanceView;
import com.grievance_management.repository.EmployeeRepository;
import com.grievance_management.security.JwtService;
import com.grievance_management.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, JwtService jwtService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(
            @RequestBody EmployeeRegisterRequest request) {
        return ResponseEntity.ok(employeeService.registerEmployee(request));
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeLoginResponse> login(
            @RequestBody EmployeeLoginRequest request) {
        System.out.println("EMAIL RECEIVED = " + request.getEmpEmail());
        return ResponseEntity.ok(employeeService.login(request));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    /* ===================== VIEW PROFILE ===================== */

    @GetMapping("/me")
    public ResponseEntity<EmployeeProfileResponse> myProfile(
            Authentication authentication) {

        String empNum = authentication.getName(); // from JWT

        System.out.println("CONTROLLER | Profile request for empNum = " + empNum);

        return ResponseEntity.ok(employeeService.getMyProfile(empNum));
    }

    /* ================= UPDATE PROFILE ================= */
//    @PutMapping("/me")
//    public ResponseEntity<Employee> updateProfile(
//            @RequestBody EmployeeUpdateRequest request,
//            HttpServletRequest httpRequest) {
//
//        String authHeader = httpRequest.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            log.error("❌ Missing or invalid Authorization header");
//            return ResponseEntity.status(401).build();
//        }
//
//        String token = authHeader.substring(7);
//
//        String empnum = jwtService.extractEmployeeId(token);
//        String role   = jwtService.extractRole(token);
//
//        log.info("➡ Update profile API called | empnum={} | role={}", empnum, role);
//
//        Employee updated =
//                employeeService.updateMyProfile(empnum, role, request);
//
//        return ResponseEntity.ok(updated);
//    }

    // get grievance using number
    @GetMapping("/api/grievances/{grievanceNum}")
    public ResponseEntity<EmployeeSingleGrievanceView> getMyGrievanceByNumber(
            @PathVariable String grievanceNum,
            Authentication authentication
    ) {
        String empNum = authentication.getName();

        EmployeeSingleGrievanceView grievance =
                employeeService.getMyGrievanceByNumber(grievanceNum, empNum);

        return ResponseEntity.ok(grievance);
    }


}
