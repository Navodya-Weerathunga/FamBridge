package com.edu.famBridge.repository;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.entity.MidwifeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MidwifeScheduleRepository extends JpaRepository<MidwifeSchedule, Long> {
    List<MidwifeSchedule> findByAvailableDate(LocalDate availableDate);

    @Query(value="SELECT * FROM midwife_schedule WHERE id=?1 ",nativeQuery = true)
    MidwifeSchedule getappointmentID(Long id);

    @Query(value = "SELECT id FROM midwife_schedule WHERE midwife_id= ?1 ORDER BY available_date DESC LIMIT 1", nativeQuery = true)
    Long findLatestOrderIdByIndexNo(String nic);

    @Query(value="SELECT * FROM midwife_schedule WHERE id=?1",nativeQuery = true)
    MidwifeSchedule updateAppointmentByID(Long id);

    @Query(value="SELECT * FROM midwife_schedule WHERE midwife_name=?1",nativeQuery = true)
    List<MidwifeSchedule> getAppointmentsByMidwifename(String midwifeName);

    @Query(value="SELECT * FROM midwife_schedule WHERE midwife_name=?1",nativeQuery = true)
    List<MidwifeSchedule> findByName(String midwife_name);

    @Query(value="SELECT * FROM midwife_schedule WHERE midwife_id=?1 AND area=?2 AND work_type='Field'",nativeQuery = true)
    List<MidwifeSchedule> getMidwifeArea( String midwifeId, String area);

    @Query("SELECT a FROM MidwifeSchedule a WHERE a.id = ?1 AND a.availableDate = ?2")
    MidwifeSchedule findByMidwifeIdAndAllocationDate(Long id, LocalDate availableDate);

    @Query(value="SELECT * FROM midwife_schedule WHERE available_date=?1 AND midwife_name=?2",nativeQuery = true)
    MidwifeSchedule getMidwifeID(LocalDate availableDate, String midwifeName);

    @Query(value="SELECT * FROM midwife_schedule WHERE available_date=?1 AND area=?2 AND work_type='Field'",nativeQuery = true)
    MidwifeSchedule getMidwifeDate(LocalDate availableDate, String area);

    @Query(value="SELECT * FROM midwife_schedule WHERE midwife_name=?1 AND id=?2",nativeQuery = true)
    MidwifeSchedule getMidwife(String midwife_name, Long id);

    @Query(value = "SELECT appointments_per_day FROM midwife_schedule WHERE id=?1 AND available_date=?2",nativeQuery = true)
    int getAllocationAppoinmentIdAndDate( Long id,  LocalDate availableDate);

    @Query(value="SELECT * FROM midwife_schedule WHERE id = ?1",nativeQuery = true)
    MidwifeScheduledto findByDid(Long id);

    @Query(value="SELECT * FROM midwife_schedule WHERE work_type='Field'",nativeQuery = true)
    List<MidwifeSchedule> findAllVisits();


    @Query(value="SELECT * FROM midwife_schedule WHERE available_date=?1 AND midwife_name=?2",nativeQuery = true)
    List<MidwifeSchedule> findByDateid(LocalDate available_date,String midwife_name);

    @Query(value="SELECT * FROM midwife_schedule WHERE area=?1 AND work_type='Field'",nativeQuery = true)
    List<MidwifeSchedule> getMidwifeDateByArea( String area);

}
