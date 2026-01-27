package com.grievance_management.repository;

import com.grievance_management.entity.OfficerGrievanceWithEmployeeView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerGrievanceWithEmployeeViewRepository
        extends JpaRepository<OfficerGrievanceWithEmployeeView, String> {
}
