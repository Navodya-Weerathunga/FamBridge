package com.edu.famBridge.repository;

import com.edu.famBridge.controller.VisitChannelingController;
import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.entity.VisitChanneling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitChannelingRepository extends JpaRepository<VisitChanneling, Long> {

        @Query(value="SELECT * FROM visit_channeling WHERE cha_id=?1 ",nativeQuery = true)
        VisitChanneling getChannelingByChannelingID(Long cha_id);

        @Query(value = "SELECT cha_id FROM visit_channeling WHERE nic= ?1 ORDER BY channeling_time DESC LIMIT 1", nativeQuery = true)
        Long findLatestOrderIdByIndexNo(String nic);


        @Query(value = "SELECT * FROM visit_channeling WHERE channeling_midwife_date= ?1 AND channeling_midwife=?2 ORDER BY channeling_time DESC LIMIT 1", nativeQuery = true)
        VisitChanneling findLatestOrderId(LocalDate channeling_midwife_date, String channeling_midwife);


        @Query(value = "SELECT * FROM visit_channeling WHERE channel_number= ?1 ", nativeQuery = true)
        Optional<VisitChanneling> findById(Integer channel_number);


        @Query(value="SELECT * FROM visit_channeling WHERE cha_id=?1",nativeQuery = true)
        VisitChanneling updateChannelingByID(Long cha_id);

        @Query(value="SELECT * FROM visit_channeling WHERE channeling_midwife_date=?1 ",nativeQuery = true)
        List<VisitChanneling> getChannelingByChannelingDate(String channeling_datetime);

        @Query(value="SELECT * FROM visit_channeling WHERE channeling_midwife=?1 ",nativeQuery = true)
        List<VisitChanneling> getChannelingByChannelingMidwife(String channeling_midwife);

        @Query(value="SELECT * FROM visit_channeling WHERE channeling_midwife_date=?1 AND channeling_midwife=?2 ",nativeQuery = true)
        List<VisitChanneling> getChannelingByChannelingDateMidwife(LocalDate channeling_datetime, String channeling_midwife);

        @Query(value="SELECT * FROM visit_channeling WHERE midwife_id=?1 AND channeling_midwife_date=?2 ",nativeQuery = true)
        List<VisitChanneling> countByDoctor_idAndAppointmentDate(Long midwife_id, LocalDate channeling_datetime);



        @Query(value = "SELECT channeling_midwife_date FROM visit_channeling WHERE cha_id= ?1", nativeQuery = true)
        String getChannelingDate(Long cha_id);

        @Query(value = "SELECT channel_number FROM visit_channeling WHERE cha_id= ?1", nativeQuery = true)
        int getChannelingNumber(Long cha_id);

        @Query(value="SELECT * FROM visit_channeling WHERE cha_id=?1",nativeQuery = true)
        VisitChanneling changeChannelingAdmin(Long cha_id);

        @Query(value="SELECT * FROM visit_channeling WHERE cha_id=?1",nativeQuery = true)
        VisitChanneling updateChanneling(Long cha_id);


        @Query(value="SELECT * FROM visit_channeling WHERE channeling_midwife_date=?1 AND nic=?2 ",nativeQuery = true)
        List<VisitChanneling>  findByDateNic(LocalDate channeling_midwife_date, String nic);

}


