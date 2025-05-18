package com.edu.famBridge.repository;

import com.edu.famBridge.entity.PregnantWomen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PregnantWomenRepository extends JpaRepository<PregnantWomen, Integer> {
    boolean existsById(Integer pregnancyRecordNo);

    List<PregnantWomen> findByEmail(String email);

    List<PregnantWomen> findByPhmArea(String phmArea);

    @Query(value = "SELECT pregnancy_record_no from pregnant_women WHERE email = ?", nativeQuery = true)
    List<Integer> findPregnancyRecordNoByEmail(String email);
}
