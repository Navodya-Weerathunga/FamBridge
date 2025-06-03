package com.edu.famBridge.repository;

import com.edu.famBridge.entity.CancelationMessage;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitChanneling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CancelationMessageRepository extends JpaRepository<CancelationMessage, Long> {

    // Update message with relevant Id database query
    @Query(value="SELECT * FROM cancelation_message WHERE id=?1",nativeQuery = true)
    CancelationMessage updateMessageByID(Long id);

    @Query(value = "SELECT id FROM cancelation_message WHERE nic= ?1 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Long findLatestCancelationIdforUser(String nic);

    @Query(value="SELECT * FROM cancelation_message WHERE id=?1 ",nativeQuery = true)
    CancelationMessage getCancelationByCancelationID(Long id);

    @Query(value="SELECT * FROM cancelation_message WHERE method='virtual'",nativeQuery = true)
    List<CancelationMessage> findAllVirtal();

    @Query(value="SELECT * FROM cancelation_message WHERE method='physical'",nativeQuery = true)
    List<CancelationMessage> findAllPhysical();
}
