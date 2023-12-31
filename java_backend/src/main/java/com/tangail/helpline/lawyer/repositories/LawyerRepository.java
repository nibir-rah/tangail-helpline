package com.tangail.helpline.lawyer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.lawyer.entity.Lawyer;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    // @Query("SELECT D FROM Lawyer d WHERE d.email = ?1")
    // Optional<Lawyer> findDOctorByEmail(String Email);
    Optional<Lawyer> findByName(String fileName);

    List<Lawyer> findByPracticeTypeId(Long typeId);

}

// practiceType
