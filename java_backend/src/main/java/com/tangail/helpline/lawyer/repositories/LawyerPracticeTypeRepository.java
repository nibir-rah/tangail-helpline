package com.tangail.helpline.lawyer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.lawyer.entity.LawyerPracticeType;

@Repository
public interface LawyerPracticeTypeRepository extends JpaRepository<LawyerPracticeType, Long> {
    // @Query("SELECT D FROM Lawyer d WHERE d.email = ?1")
    // Optional<Lawyer> findDOctorByEmail(String Email);
    Optional<LawyerPracticeType> findByName(String fileName);

}
