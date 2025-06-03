package com.edu.famBridge.controller;


import com.edu.famBridge.dto.PregnantWomenDTO;
import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.repository.PregnantWomenRepository;
import com.edu.famBridge.service.PregnantWomenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "famBridge/pregnantWomen")
@CrossOrigin(origins = "http://localhost:3000")
public class PregnantWomenController {
    @Autowired
    private PregnantWomenService pregnantWomenService;
    @Autowired
    private PregnantWomenRepository pregnantWomenRepository;

    @GetMapping("/getWomenByNic/{nic}")//Get women by nic  -----used------
    public PregnantWomenDTO getWomenByNic(@Valid @PathVariable String nic) {
        return pregnantWomenService.getWomenByNic(nic);
    }

    @GetMapping("/getAllWomen/{nic}")
    public ResponseEntity<List<PregnantWomenDTO>> getAllWomenByNic(@PathVariable String nic) {
        List<PregnantWomenDTO> result = pregnantWomenService.getAllWomenByNic(nic);
        return ResponseEntity.ok(result);
    }




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



