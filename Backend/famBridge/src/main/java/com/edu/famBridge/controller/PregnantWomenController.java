package com.edu.famBridge.controller;

import com.edu.famBridge.dto.PregnantWomenDTO;
import com.edu.famBridge.service.PregnantWomenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("famBridge/pregnantWomen")

public class PregnantWomenController {
    @Autowired
    private PregnantWomenService pregnantWomenService;

    @PostMapping("/{requestId}")
    public ResponseEntity<PregnantWomenDTO> savePregnantWomen (@PathVariable Long requestId, @RequestBody PregnantWomenDTO pregnantWomenDTO){
        return ResponseEntity.ok(pregnantWomenService.savePregnantWomen(requestId, pregnantWomenDTO));
    }

    @GetMapping("/{phmArea}")
    public ResponseEntity<List<PregnantWomenDTO>> getAllPregnantWomen (@PathVariable String phmArea){
        return ResponseEntity.ok(pregnantWomenService.getAllPregnantWomen(phmArea));
    }

    @GetMapping("pregnancyCards/{email}")
    public ResponseEntity<List<Integer>> getPregnancyCards (@PathVariable String email){
        return ResponseEntity.ok(pregnantWomenService.getPregnancyCards(email));
    }
}
