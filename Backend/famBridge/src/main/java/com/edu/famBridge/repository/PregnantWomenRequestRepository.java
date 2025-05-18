package com.edu.famBridge.repository;

import com.edu.famBridge.entity.PregnantWomenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PregnantWomenRequestRepository extends JpaRepository<PregnantWomenRequest, Long> {

    List<PregnantWomenRequest> findByEmail(String email);

    @Query(value = "SELECT request_id, first_name, email, nic, phm_area, assign_midwife, contact_no, " +
            "request_date, status FROM pregnant_women_request WHERE phm_area = ?", nativeQuery = true)
    List<Object[]> findRequestDetailsByPhmArea(String phmArea);
}
