package com.grievance_management.repository;

import com.grievance_management.entity.OfficerSingleGrievanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficerSingleGrievanceViewRepository
        extends JpaRepository<OfficerSingleGrievanceView, String> {

    Optional<OfficerSingleGrievanceView> findByGrievanceNum(String grievanceNum);

}
