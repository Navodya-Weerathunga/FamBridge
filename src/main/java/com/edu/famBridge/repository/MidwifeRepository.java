package com.edu.famBridge.repository;

import com.edu.famBridge.entity.Midwife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MidwifeRepository extends JpaRepository<Midwife, Long> {
    @Query("SELECT m FROM Midwife m WHERE m.email = :email")
    Midwife findByEmails(String email);

    @Query("SELECT m FROM Midwife m WHERE m.fullName = :midwifeId")
    Optional<Midwife> findByName(String midwifeId);

    List<Midwife> findAll();

        @Query("SELECT m FROM Midwife m WHERE m.nic = :nic")
        Optional<Midwife> findByNic(String nic);

        Optional<Midwife> findByEmail(String email);

        @Query("SELECT m.fullName FROM Midwife m JOIN m.workingArea area WHERE area = :area")
        List<String> findFullNameByArea(@Param("area") String area);


    }


