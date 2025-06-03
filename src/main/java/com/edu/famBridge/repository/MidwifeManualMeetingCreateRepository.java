package com.edu.famBridge.repository;

import com.edu.famBridge.entity.MidwifeManualMeetingCreate;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitChanneling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MidwifeManualMeetingCreateRepository extends JpaRepository<MidwifeManualMeetingCreate, Long> {
    @Query(value="SELECT * FROM  midwife_manual_meeting_create WHERE id=?1 ",nativeQuery = true)
    MidwifeManualMeetingCreate getchannelingID(Long cha_id);

}
