package com.edu.famBridge.repository;

import com.edu.famBridge.entity.MarriedCouple;
import com.edu.famBridge.entity.MarriedCoupleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarriedCoupleRepository extends JpaRepository<MarriedCouple, Long> {
    @Query(value="SELECT * FROM married_couple WHERE nic=?1 ",nativeQuery = true)
    MarriedCouple getCoupleByNic(String nic);

        Optional<MarriedCouple> findByEmail(String email);

        List<MarriedCouple> findByPhmArea(String phmArea);

        Optional<MarriedCouple> findByMarriedCoupleId (Long marriedCoupleId);



}
