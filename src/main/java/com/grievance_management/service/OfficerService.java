package com.grievance_management.service;

import com.grievance_management.dto.OfficerLoginRequest;
import com.grievance_management.dto.OfficerLoginResponse;
import com.grievance_management.dto.OfficerProfileResponse;
import com.grievance_management.dto.OfficerRegisterRequest;
import com.grievance_management.entity.Officer;
import com.grievance_management.entity.OfficerGrievanceView;
import com.grievance_management.entity.OfficerGrievanceWithEmployeeView;
import com.grievance_management.entity.OfficerSingleGrievanceView;
import com.grievance_management.exception.ResourceNotFoundException;
import com.grievance_management.repository.*;
import com.grievance_management.security.JwtService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfficerService {

    private final InvestigationRepository investigationRepository;
    private final OfficerGrievanceViewRepository grievanceViewRepository;
    private final OfficerSingleGrievanceViewRepository singleGrievanceViewRepository;
    private final OfficerGrievanceWithEmployeeViewRepository grievanceRepo;
    private final OfficerRepository officerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private String generateAuthKey() {
        // 6-character alphanumeric auth key
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String registerOfficer(OfficerRegisterRequest request) {

        // Initialize DB actor context
        entityManager
                .createNativeQuery("SET @actor_id = 'SYSTEM'")
                .executeUpdate();

        entityManager
                .createNativeQuery("SET @actor_role = 'OFFICER'")
                .executeUpdate();

        String authKey = generateAuthKey();

        Officer officer = new Officer();
        officer.setOfficernum(request.getOfficernum());
        officer.setOfficername(request.getOfficername());
        officer.setOfficerEmail(request.getOfficerEmail());
        officer.setCtgnum(request.getCtgnum());
        officer.setAddress(request.getAddress());
        officer.setPassword(passwordEncoder.encode(request.getPassword()));
        officer.setAuthKey(authKey);

        officerRepository.save(officer);

        return authKey;
    }
    public OfficerLoginResponse login(OfficerLoginRequest request) {

        Officer officer = officerRepository
                .findByOfficerEmailAndAuthKey(
                        request.getOfficerEmail(),
                        request.getAuthKey()
                )
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateToken(
                officer.getOfficernum(),
                "OFFICER"
        );

        return new OfficerLoginResponse(token,officer.getOfficerEmail());
    }

    public OfficerProfileResponse getMyProfile(String officerEmail) {

        Officer officer = officerRepository.findByOfficerEmail(officerEmail)
                .orElseThrow(() -> new RuntimeException("Officer not found"));

        return new OfficerProfileResponse(
                officer.getOfficernum(),
                officer.getOfficername(),
                officer.getOfficerEmail(),
                officer.getCtgnum(),
                officer.getAddress()
        );
    }

    public List<OfficerGrievanceView> getAllGrievances() {
        return grievanceViewRepository.findAll();
    }

    public Optional<OfficerSingleGrievanceView> getGrievanceByNumber(String grievanceNum) {

        Optional<OfficerSingleGrievanceView> grievance =
                singleGrievanceViewRepository.findByGrievanceNum(grievanceNum);

        grievance.ifPresent(g ->
                System.out.println("SERVICE DATA => " + g)
        );

        return grievance;
    }

    public List<OfficerGrievanceWithEmployeeView> getAllGrievancesWithEmployee() {
        return grievanceRepo.findAll();
    }



    @Transactional
    public String chooseGrievance(
            String grievanceNum,
            String officerNum
    ) {
        return investigationRepository.assignGrievance(
                grievanceNum,
                officerNum
        );
    }

}
