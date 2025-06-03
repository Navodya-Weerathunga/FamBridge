package com.edu.famBridge.repository;

import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.entity.VisitChanneling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PregnantWomenRepository extends JpaRepository<PregnantWomen, Integer> {

    @Query(value="SELECT * FROM pregnant_women WHERE nic=?1 ORDER BY registered_date DESC LIMIT 1",nativeQuery = true)
    PregnantWomen getwomenByNic(String nic);


//    @Query(value="SELECT * FROM pregnant_women WHERE nic=?1 ",nativeQuery = true)
//    List<PregnantWomen> findAllByNIC(String nic);
    @Query("SELECT p FROM PregnantWomen p WHERE p.nic = :nic")
    List<PregnantWomen> findByNic(@Param("nic") String nic);



  boolean existsById(Integer pregnancyRecordNo);

        List<PregnantWomen> findByEmail(String email);

        List<PregnantWomen> findByPhmArea(String phmArea);

        @Query(value = "SELECT pregnancy_record_no from pregnant_women WHERE email = ?", nativeQuery = true)
        List<Integer> findPregnancyRecordNoByEmail(String email);
    }


