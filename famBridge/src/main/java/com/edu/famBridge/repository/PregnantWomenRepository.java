package com.edu.famBridge.repository;


import com.edu.famBridge.entity.PregnantWomen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PregnantWomenRepository  extends JpaRepository<PregnantWomen, Integer> {
}
