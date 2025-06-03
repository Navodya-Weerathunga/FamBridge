package com.edu.famBridge.repository;


import com.edu.famBridge.entity.AdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceRepository extends JpaRepository<AdviceEntity, Long> {
    List<AdviceEntity> findByType(String type);
}
