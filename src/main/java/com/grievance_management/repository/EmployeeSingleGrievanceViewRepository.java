package com.grievance_management.repository;

import com.grievance_management.entity.EmployeeSingleGrievanceView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeSingleGrievanceViewRepository
        extends JpaRepository<EmployeeSingleGrievanceView, Long> {

    Optional<EmployeeSingleGrievanceView>
    findByGrievanceNumAndEmpNum(String grievanceNum, String empNum);
}
