package com.tangail.helpline.doctor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.doctor.entity.SymptomImage;

@Repository
public interface ImageSympRepository extends JpaRepository<SymptomImage, Long> {
    Optional<SymptomImage> findByName(String fileName);

}
