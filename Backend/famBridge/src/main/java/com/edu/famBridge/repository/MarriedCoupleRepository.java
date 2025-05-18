package com.edu.famBridge.repository;

import com.edu.famBridge.entity.MarriedCouple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MarriedCoupleRepository extends JpaRepository<MarriedCouple, Long> {

    Optional<MarriedCouple> findByEmail(String email);

    List<MarriedCouple> findByPhmArea(String phmArea);

    Optional<MarriedCouple> findByMarriedCoupleId (Long marriedCoupleId);
}
