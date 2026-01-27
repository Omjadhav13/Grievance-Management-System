package com.grievance_management.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class InvestigationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public String assignGrievance(
            String grievanceNum,
            String officerNum
    ) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("assign_grievance");

        query.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.OUT);

        query.setParameter("p_grvnnum", grievanceNum);
        query.setParameter("p_actor_id", officerNum);
        query.setParameter("p_actor_role", "OFFICER");

        query.execute();

        return (String) query.getOutputParameterValue("p_investigationnum");
    }
}
