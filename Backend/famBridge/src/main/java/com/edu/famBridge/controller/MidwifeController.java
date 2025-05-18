package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MarriedCoupleUpdateDTO;
import com.edu.famBridge.dto.MidwifeDTO;
import com.edu.famBridge.dto.MidwifeUpdateDTO;
import com.edu.famBridge.entity.Midwife;
import com.edu.famBridge.service.MidwifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "famBridge/midwife")

public class MidwifeController {
    @Autowired
    private MidwifeService midwifeService;

    @PostMapping("/")
    public ResponseEntity<MidwifeDTO> saveMidwife (@RequestBody MidwifeDTO midwifeDto){
        return ResponseEntity.ok(midwifeService.saveMidwife(midwifeDto));
    }

    @PostMapping("/sendLoginDetails/{email}")
    public String sendLoginDetails(@PathVariable("email") String email, @PathVariable("password") String password ){
        midwifeService.sendLoginDetails(email, password);
        return "Login details sent successfully.";
    }

    @PostMapping("/login")
    public ResponseEntity<MidwifeDTO>loginMidwife(@RequestBody MidwifeDTO midwifeDTO){
        return  ResponseEntity.ok(midwifeService.loginMidwife(midwifeDTO));
    }

    @GetMapping("/midwives")
    public ResponseEntity<MidwifeDTO> getAllMidwife (){
        return ResponseEntity.ok(midwifeService.getAllMidwives());
    }

    @GetMapping("/{email}")
    public ResponseEntity<MidwifeDTO> getMidwifeByEmail (@PathVariable String email){
        return ResponseEntity.ok(midwifeService.getMidwifeByEmail(email));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<MidwifeDTO>updatePassword(@RequestBody MidwifeDTO midwifeDTO){
        return  ResponseEntity.ok(midwifeService.updatePassword(midwifeDTO));
    }

    @GetMapping("/workingArea/{workingArea}")
    public ResponseEntity<?> getMidwivesByWorkingArea(@PathVariable String workingArea) {
        List<String> midwives = midwifeService.findMidwifeByWorkingArea(workingArea);

        if (!midwives.isEmpty()) {
            return ResponseEntity.ok(midwives);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No midwives found for the given working area: " + workingArea);
        }
    }


    @PutMapping("updateProfile/{email}")
    public ResponseEntity<MidwifeUpdateDTO> updateProfile (@PathVariable String email, @RequestBody MidwifeUpdateDTO midwifeUpdateDTO){
        return  ResponseEntity.ok(midwifeService.updateProfile(email, midwifeUpdateDTO));
    }


}
