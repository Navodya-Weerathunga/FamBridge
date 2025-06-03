package com.edu.famBridge.repository;

import com.edu.famBridge.entity.VisitReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VisitReminderRepository extends JpaRepository<VisitReminder, Long> {

    @Query(value="SELECT * FROM visit_reminder WHERE id=?1 ",nativeQuery = true)
    VisitReminder  findByIDOfreminder(Long id);


    @Query(value="SELECT * FROM visit_reminder WHERE visiting_date=?1 AND midwife_name=?2 ",nativeQuery = true)
    List<VisitReminder> findByDateid(LocalDate visiting_date, String midwife_name);
}
