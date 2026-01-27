package com.grievance_management.repository;

import com.grievance_management.entity.Grievance;
import com.grievance_management.entity.GrievanceEmployeeView;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, String> {

    /* ===================== APPLY GRIEVANCE ===================== */

    @Modifying
    @Transactional
    @Query(
            value = "CALL file_grievance(:ctgnum, :subject, :description, :severity, :actorId, :actorRole)",
            nativeQuery = true
    )
    void fileGrievance(
            @Param("ctgnum") String ctgnum,
            @Param("subject") String subject,
            @Param("description") String description,
            @Param("severity") String severity,
            @Param("actorId") String actorId,
            @Param("actorRole") String actorRole
    );

    /* ===================== EMPLOYEE: VIEW OWN ===================== */

    @Query(value = "CALL fetch_my_grievances(:actorId, :actorRole)", nativeQuery = true)
    List<GrievanceEmployeeView> fetchMyGrievances(
            @Param("actorId") String actorId,
            @Param("actorRole") String actorRole
    );

    /* ===================== (FUTURE) OFFICER / ADMIN ===================== */

    @Query(value = "CALL fetch_all_grievances(:actorId, :actorRole)", nativeQuery = true)
    List<Grievance> fetchAllGrievances(
            @Param("actorId") String actorId,
            @Param("actorRole") String actorRole
    );
}
