package com.tangail.helpline.doctor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tangail.helpline.doctor.entity.DepartmentImage;

@Repository
public interface ImageDeptRepository extends JpaRepository<DepartmentImage, Long> {
    Optional<DepartmentImage> findByName(String fileName);

}
