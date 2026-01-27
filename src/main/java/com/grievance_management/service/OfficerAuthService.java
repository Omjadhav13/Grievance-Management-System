package com.grievance_management.service;

import com.grievance_management.dto.OfficerLoginRequest;
import com.grievance_management.dto.OfficerLoginResponse;
import com.grievance_management.repository.OfficerAuthRepository;
import com.grievance_management.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfficerAuthService {

    private final OfficerAuthRepository repository;
    private final JwtService jwtService;

    public OfficerLoginResponse login(OfficerLoginRequest request) {

        Object[] result = repository.loginOfficer(
                request.getOfficerEmail(),
                request.getPassword(),
                request.getAuthKey()
        );

        String officerNum = (String) result[0];
        String role = (String) result[1];

        log.info("Officer logged in: {}", officerNum);

        String token = jwtService.generateToken(officerNum, role);

        return new OfficerLoginResponse(token, role);
    }
}
