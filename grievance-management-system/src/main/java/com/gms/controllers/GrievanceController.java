package com.gms.controllers;

import com.gms.dto.GrievanceDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grievances")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @GetMapping("/{grvnNum}")
    public ResponseEntity<GrievanceDTO> getGrievanceByNum(
            @PathVariable String grvnNum) {

        GrievanceDTO dto = grievanceService.getGrievanceByNum(grvnNum);
        return ResponseEntity.ok(dto);
    }
}
