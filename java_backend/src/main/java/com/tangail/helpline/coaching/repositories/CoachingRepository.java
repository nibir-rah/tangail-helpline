package com.tangail.helpline.coaching.repositories;

import java.util.Optional;

import com.tangail.helpline.coaching.entity.Coaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachingRepository extends JpaRepository<Coaching, Long> {
    Optional<Coaching> findByName(String fileName);

}
