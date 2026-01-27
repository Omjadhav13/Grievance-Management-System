package com.grievance_management.service;

import com.grievance_management.dto.GrievanceRequest;
import com.grievance_management.entity.Grievance;
import com.grievance_management.entity.GrievanceEmployeeView;
import com.grievance_management.repository.GrievanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrievanceService {

    private final GrievanceRepository grievanceRepository;

    public GrievanceService(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    /* ===================== APPLY ===================== */

    @Transactional
    public void applyGrievance(GrievanceRequest request, String empNum) {

        System.out.println("📝 SERVICE | Applying grievance");
        System.out.println("➡ empNum   = " + empNum);
        System.out.println("➡ category = " + request.getCtgnum());

        grievanceRepository.fileGrievance(
                request.getCtgnum(),
                request.getSubject(),
                request.getDescription(),
                request.getSeverity(),
                empNum,
                "EMPLOYEE"
        );

        System.out.println("✅ SERVICE | Grievance filed");
    }

    /* ===================== EMPLOYEE: VIEW OWN ===================== */

    public List<GrievanceEmployeeView> getMyGrievances(String empNum, String role) {

        System.out.println("📌 SERVICE | Fetch my grievances");
        System.out.println("➡ empNum = " + empNum);
        System.out.println("➡ role   = " + role);

        List<GrievanceEmployeeView> list =
                grievanceRepository.fetchMyGrievances(empNum, role);

        System.out.println("📌 SERVICE | Found = " + list.size());

        return list;
    }

    /* ===================== (FUTURE) OFFICER / ADMIN ===================== */

    public List<Grievance> getAllGrievances(String actorId, String role) {

        System.out.println("📌 SERVICE | Fetch all grievances");
        System.out.println("➡ actorId = " + actorId);
        System.out.println("➡ role    = " + role);

        return grievanceRepository.fetchAllGrievances(actorId, role);
    }
}
