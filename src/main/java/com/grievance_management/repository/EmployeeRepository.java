package com.grievance_management.repository;

import com.grievance_management.entity.Employee;
import com.grievance_management.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmpEmail(String empEmail);

    Optional<Employee> findByEmpnum(String empnum);

    @Modifying
    @Transactional
    @Query(
            value = "CALL sp_update_employee(" +
                    ":empnum, :empname, :empEmail, :department, :role, :address, :contactNum, :actorId, :actorRole)",
            nativeQuery = true
    )
    void updateEmployee(
            @Param("empnum") String empnum,
            @Param("empname") String empname,
            @Param("empEmail") String empEmail,
            @Param("department") String department,
            @Param("role") String role,
            @Param("address") String address,
            @Param("contactNum") String contactNum,
            @Param("actorId") String actorId,
            @Param("actorRole") String actorRole
    );

}
