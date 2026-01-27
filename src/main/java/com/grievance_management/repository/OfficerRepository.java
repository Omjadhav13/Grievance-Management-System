package com.grievance_management.repository;

import com.grievance_management.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, String> {

    Optional<Officer> findByOfficerEmail(String officerEmail);

    Optional<Officer> findByOfficerEmailAndAuthKey(
            String officerEmail,
            String authKey
    );
}
