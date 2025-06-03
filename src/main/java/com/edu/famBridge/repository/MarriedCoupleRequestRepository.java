package com.edu.famBridge.repository;

import com.edu.famBridge.entity.MarriedCoupleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarriedCoupleRequestRepository extends JpaRepository<MarriedCoupleRequest, Long> {

    Optional<Object> findByRequestId(long requestId);

        Optional<MarriedCoupleRequest> findByNic(String nic);


        @Query(value = "SELECT request_id, first_name, last_name, email, nic, moh_area, grama_niladhari_division, address, " +
                "request_date, request_status FROM married_couple_request", nativeQuery = true)
        List<Object[]> findRequestDetails();

        @Query(value = "SELECT first_name, last_name, email, nic, moh_area, grama_niladhari_division, address, request_date FROM married_couple_request where request_id = ?1", nativeQuery = true)
        List<Object[]> findRequestDetailsById(Long requestId);

        @Query(value = "SELECT request_id, first_name, last_name, email, nic, moh_area, grama_niladhari_division, address, " +
                "request_date, request_status FROM married_couple_request WHERE moh_area = ?", nativeQuery = true)
        List<Object[]> findRequestDetailsByMohArea(String mohArea);
    }



