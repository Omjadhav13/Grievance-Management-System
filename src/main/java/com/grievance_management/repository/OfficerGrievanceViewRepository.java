package com.grievance_management.repository;

import com.grievance_management.entity.OfficerGrievanceView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerGrievanceViewRepository
        extends JpaRepository<OfficerGrievanceView, String> {
}