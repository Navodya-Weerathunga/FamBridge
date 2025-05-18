package com.edu.famBridge.serviceImpl;


import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.repository.PregnantWomenRepository;
import com.edu.famBridge.repository.PregnantWomenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PregnantWomenService {

    @Autowired
    private PregnantWomenRepository repository;

    public PregnantWomen addPregnancyRecord(PregnantWomen record){
        return repository.save(record);
    }

    public List<PregnantWomen> getAllPregnancyRecords(){
        return repository.findAll();
    }

}
