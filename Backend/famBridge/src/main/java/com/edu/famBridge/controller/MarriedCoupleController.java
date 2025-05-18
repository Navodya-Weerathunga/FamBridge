package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MarriedCoupleDTO;
import com.edu.famBridge.dto.MarriedCoupleUpdateDTO;
import com.edu.famBridge.service.MarriedCoupleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "famBridge/marriedCouple/")

public class MarriedCoupleController {

    @Autowired
    private MarriedCoupleService marriedCoupleService;

    @PostMapping("/marriedCouple")
    public ResponseEntity<MarriedCoupleDTO> saveMarriedCouple (@RequestBody MarriedCoupleDTO marriedCoupleDTO){
        return ResponseEntity.ok(marriedCoupleService.saveMarriedCouple(marriedCoupleDTO));
    }

    @GetMapping("/marriedCouples/{phmArea}")
    public ResponseEntity<List<MarriedCoupleDTO>> getAllMarriedCouples (@PathVariable String phmArea){
        return ResponseEntity.ok(marriedCoupleService.getAllMarriedCouples(phmArea));
    }

    @PostMapping("/login")
    public ResponseEntity<MarriedCoupleDTO>loginMarriedCouple(@RequestBody MarriedCoupleDTO marriedCoupleDTO){
        return  ResponseEntity.ok(marriedCoupleService.loginMarriedCouple(marriedCoupleDTO));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<MarriedCoupleDTO>updatePassword(@RequestBody MarriedCoupleDTO marriedCoupleDTO){
        return  ResponseEntity.ok(marriedCoupleService.updatePassword(marriedCoupleDTO));
    }

    @GetMapping("details/{marriedCoupleId}")
    public ResponseEntity<MarriedCoupleDTO> getMarriedCoupleDetailsById (@PathVariable Long marriedCoupleId){
        return ResponseEntity.ok(marriedCoupleService.getMarriedCoupleDetailsById(marriedCoupleId));
    }

    @GetMapping("userProfile/{email}")
    public ResponseEntity<MarriedCoupleDTO> getMarriedCoupleDetailsById (@PathVariable String email){
        return ResponseEntity.ok(marriedCoupleService.getMarriedCoupleDetailsByEmail(email));
    }

    @PutMapping("updateProfile/{email}")
    public ResponseEntity<MarriedCoupleUpdateDTO> updateProfile (@PathVariable String email, @RequestBody MarriedCoupleUpdateDTO marriedCoupleUpdateDTO){
        return  ResponseEntity.ok(marriedCoupleService.updateProfile(email, marriedCoupleUpdateDTO));
    }
}
