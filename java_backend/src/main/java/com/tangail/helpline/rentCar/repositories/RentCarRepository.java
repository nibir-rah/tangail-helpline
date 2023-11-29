package com.tangail.helpline.rentCar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.rentCar.entity.RentCar;

@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Long> {
    Optional<RentCar> findByName(String fileName);

}
