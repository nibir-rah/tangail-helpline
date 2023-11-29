package com.tangail.helpline.doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.doctor.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // @Query("SELECT D FROM Doctor d WHERE d.email = ?1")
    // Optional<Doctor> findDOctorByEmail(String Email);
}
