package com.tangail.helpline.lawyer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.lawyer.entity.LawyerCourt;

@Repository
public interface LawyerCourtRepository extends JpaRepository<LawyerCourt, Long> {
    Optional<LawyerCourt> findByName(String fileName);

}
