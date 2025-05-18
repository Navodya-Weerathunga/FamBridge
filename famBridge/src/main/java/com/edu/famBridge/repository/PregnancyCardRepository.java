package com.edu.famBridge.repository;

import com.edu.famBridge.entity.PregnancyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PregnancyCardRepository extends JpaRepository<PregnancyCard, Integer> {
    Optional<PregnancyCard> findByPregnantWomen_PregnancyRecordNo(Integer pregnancyRecordNo);

    List<PregnancyCard> findAllByPregnantWomen_PregnancyRecordNo(Integer pregnancyRecordNo);





}





