package com.tangail.helpline.rentCar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.rentCar.entity.RentCarImage;

@Repository
public interface ImageRentCarRepository extends JpaRepository<RentCarImage, Long> {
    Optional<RentCarImage> findByName(String fileName);

    Optional<RentCarImage> findByFilePath(String filePath);

}
