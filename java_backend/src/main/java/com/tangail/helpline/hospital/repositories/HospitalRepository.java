package com.tangail.helpline.hospital.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.hospital.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // @Query("SELECT D FROM Hospital d WHERE d.email = ?1")
    // Optional<Hospital> findDOctorByEmail(String Email);
    Optional<Hospital> findByName(String fileName);

    List<Hospital> findByType(String type);

}
