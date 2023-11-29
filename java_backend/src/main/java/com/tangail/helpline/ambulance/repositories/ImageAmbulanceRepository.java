package com.tangail.helpline.ambulance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.ambulance.entity.AmbulanceImage;

@Repository
public interface ImageAmbulanceRepository extends JpaRepository<AmbulanceImage, Long> {
    Optional<AmbulanceImage> findByName(String fileName);

    Optional<AmbulanceImage> findByFilePath(String filePath);

}
