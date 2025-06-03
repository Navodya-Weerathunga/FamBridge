package com.edu.famBridge.repository;

import com.edu.famBridge.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    //  Find suggestions by email
    List<Suggestion> findByEmail(String email);
}
