package com.grievance_management.controller;

import com.grievance_management.dto.ChooseGrievanceRequest;
import com.grievance_management.dto.OfficerLoginRequest;
import com.grievance_management.dto.OfficerProfileResponse;
import com.grievance_management.dto.OfficerRegisterRequest;
import com.grievance_management.entity.OfficerGrievanceView;
import com.grievance_management.entity.OfficerGrievanceWithEmployeeView;
import com.grievance_management.entity.OfficerSingleGrievanceView;
import com.grievance_management.service.OfficerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/officers")
public class OfficerController {

    private final OfficerService officerService;

    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody OfficerRegisterRequest request) {
        String authKey = officerService.registerOfficer(request);
        return ResponseEntity.ok(
                Map.of(
                        "message", "Officer registered successfully",
                        "authKey", authKey
                )
        );
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody OfficerLoginRequest request) {
        return ResponseEntity.ok(officerService.login(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<OfficerProfileResponse> getMyProfile(
            Authentication authentication) {

        String officerEmail = authentication.getName();

        return ResponseEntity.ok(
                officerService.getMyProfile(officerEmail)
        );
    }

    @GetMapping("/grievances")
    public ResponseEntity<List<OfficerGrievanceView>> getAllGrievances() {
        return ResponseEntity.ok(officerService.getAllGrievances());
    }

    //single grievance
    @GetMapping("/grievances/{grvnnum}")
    public ResponseEntity<?> getSingleGrievance(@PathVariable String grvnnum) {

        Optional<OfficerSingleGrievanceView> grievance =
                officerService.getGrievanceByNumber(grvnnum);

        if (grievance.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", "No grievance found for number " + grvnnum
                    ));
        }

        System.out.println("CONTROLLER DATA => " + grievance.get());
        return ResponseEntity.ok(grievance.get());
    }

    //GRIEVNACE WITH EMPLOYEES
    @GetMapping("/grievances/with-employee")
    public ResponseEntity<List<OfficerGrievanceWithEmployeeView>>
    getGrievancesWithEmployeeDetails() {

        return ResponseEntity.ok(
                officerService.getAllGrievancesWithEmployee()
        );
    }



    @PostMapping("/chooseGrievance")
    public ResponseEntity<?> chooseGrievance(
            @RequestBody ChooseGrievanceRequest request,
            Authentication authentication
    ) {
        String officerNum = authentication.getName(); // comes from JWT (sub)

        String investigationNum =
                officerService.chooseGrievance(
                        request.getGrievanceNum(),
                        officerNum
                );

        return ResponseEntity.ok(
                Map.of(
                        "message", "Grievance assigned successfully",
                        "investigationNum", investigationNum
                )
        );
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<?> logout(Authentication authentication) {

        String officerNum = authentication.getName();

        // Optional log
        System.out.println("Officer logged out: " + officerNum);

        return ResponseEntity.ok(
                Map.of("message", "Logout successful")
        );
    }


}
