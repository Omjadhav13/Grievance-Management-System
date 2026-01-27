package com.grievance_management.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class OfficerAuthRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Object[] loginOfficer(String email, String password, String authKey) {
        return (Object[]) entityManager
                .createNativeQuery("CALL sp_login_officer(?,?,?)")
                .setParameter(1, email)
                .setParameter(2, password)
                .setParameter(3, authKey)
                .getSingleResult();
    }
}
