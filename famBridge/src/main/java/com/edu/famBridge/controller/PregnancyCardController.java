package com.edu.famBridge.controller;

import com.edu.famBridge.dto.PregnancyCardDTO;

import com.edu.famBridge.serviceImpl.PregnancyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("famBridge/pregnancyCard")
@CrossOrigin(origins = "http://localhost:3000")
public class PregnancyCardController {

    @Autowired
    private PregnancyCardService service;

    @PostMapping
    public ResponseEntity<PregnancyCardDTO> addPregnancyCard(@RequestBody PregnancyCardDTO dto){
        PregnancyCardDTO savedCard = service.addPregnancyCard(dto);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping
    public ResponseEntity<List<PregnancyCardDTO>> getAllPregnancyCards(){
        List<PregnancyCardDTO> cards = service.getAllPregnancyCards();
        return ResponseEntity.ok(cards);
    }


    @GetMapping("/by-pregnancyRecordNo/{pregnancyRecordNo}")
    public ResponseEntity<List<PregnancyCardDTO>> getCardsByRecordNo(@PathVariable Integer pregnancyRecordNo) {
        List<PregnancyCardDTO> cards = service.getPregnancyCardsByPregnancyRecordNo(pregnancyRecordNo);
        return ResponseEntity.ok(cards);
    }


    @PutMapping("/by-pregnancyRecordNo/{pregnancyRecordNo}")
    public ResponseEntity<PregnancyCardDTO> updateByPregnancyRecordNo(@PathVariable Integer pregnancyRecordNo, @RequestBody PregnancyCardDTO updatedDto){
        PregnancyCardDTO card = service.updatePregnancyCardByPregnancyRecordNo(pregnancyRecordNo, updatedDto);
        return (card != null)? ResponseEntity.ok(card):ResponseEntity.notFound().build();
    }


}
