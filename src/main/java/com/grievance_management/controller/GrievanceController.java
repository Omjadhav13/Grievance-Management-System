package com.grievance_management.controller;

import com.grievance_management.dto.GrievanceRequest;
import com.grievance_management.entity.Grievance;
import com.grievance_management.entity.GrievanceEmployeeView;
import com.grievance_management.service.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    /* ===================== APPLY ===================== */

    @PostMapping("/apply")
    public ResponseEntity<String> applyGrievance(
            @RequestBody GrievanceRequest request,
            Authentication authentication
    ) {
        String empNum = authentication.getName();
        grievanceService.applyGrievance(request, empNum);
        return ResponseEntity.ok("Grievance filed successfully");
    }

    /* ===================== EMPLOYEE VIEW ===================== */

    /* ===================== EMPLOYEE: VIEW OWN ===================== */

    @GetMapping("/my")
    public ResponseEntity<?> myGrievances(Authentication authentication) {

        String empNum = authentication.getName();
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        System.out.println("🔐 CONTROLLER | empNum = " + empNum);
        System.out.println("🔐 CONTROLLER | role   = " + role);

        List<GrievanceEmployeeView> grievances =
                grievanceService.getMyGrievances(empNum, role);

        if (grievances.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No grievance has been filed yet");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(grievances);
    }

    /* ===================== ADMIN / OFFICER VIEW ===================== */

    @GetMapping("/all")
    public List<Grievance> allGrievances(Authentication authentication) {
        return grievanceService.getAllGrievances(
                authentication.getName(),
                authentication.getAuthorities().iterator().next().getAuthority()
        );
    }
}
