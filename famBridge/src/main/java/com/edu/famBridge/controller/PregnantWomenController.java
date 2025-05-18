package com.edu.famBridge.controller;


import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.serviceImpl.PregnantWomenService;
import com.edu.famBridge.serviceImpl.PregnantWomenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/famBridge/pregnancyRecord")
public class PregnantWomenController {

    @Autowired
    private PregnantWomenService service;

    @PostMapping
    public ResponseEntity<PregnantWomen> addPregnancyRecord(@RequestBody PregnantWomen record){
        PregnantWomen saveRecord = service.addPregnancyRecord(record);
        return ResponseEntity.ok(saveRecord);
    }

    @GetMapping
    public ResponseEntity<List<PregnantWomen>> getAllPregnancyRecords(){
        List<PregnantWomen> records = service.getAllPregnancyRecords();
        return ResponseEntity.ok(records);
    }
}
