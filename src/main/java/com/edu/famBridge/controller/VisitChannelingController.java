package com.edu.famBridge.controller;


import com.edu.famBridge.dto.Responsedto;
import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.entity.VisitChanneling;
import com.edu.famBridge.repository.VisitChannelingRepository;
import com.edu.famBridge.service.VisitChannelingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "famBridge/VisitChanneling")
@CrossOrigin(origins = "http://localhost:3000")
public class VisitChannelingController {


        @Autowired
        private VisitChannelingService visitChannelingService;

        @Autowired
        private VisitChannelingRepository visitChannelingRepository;

        @GetMapping("/getChannelings")//Get all channelings
        public List<VisitChannelingdto> getChanneling() {
            return visitChannelingService.getAllChannelings();
        }

        @PutMapping("/changeChannelingAdmin/{cha_id}")//update channeling by id by admin       -----used------
        public VisitChannelingdto changeChannelingAdmin(@Valid @PathVariable Long cha_id , @Valid @RequestBody VisitChannelingdto visitChannelingdto) {
            return visitChannelingService.changeChannelingAdmin(cha_id,visitChannelingdto);
        }

        @DeleteMapping("/deleteChannelingByChannelingID/{cha_id}")// Delete Channeling by id   -----used------
        public boolean deleteChannelingByChannelingID(@Valid @PathVariable Long cha_id) {

            return visitChannelingService.deleteChannelingByChannelingID(cha_id);
        }

        @GetMapping("/getChannelingByChannelingID/{cha_id}")// Get channeling by channeling id    -----used------
        public VisitChannelingdto getChannelingByChannelingID(@Valid @PathVariable Long cha_id) {
            return visitChannelingService.getChannelingByChannelingID(cha_id);
        }

        @GetMapping("/getChannelingByChannelingDate/{channeling_datetime}")//Get channeling by date    -----used------
        public List<VisitChannelingdto> getChannelingByChannelingDate(@Valid @PathVariable String channeling_datetime) {
            return visitChannelingService.getChannelingByChannelingDate(channeling_datetime);
        }

        @GetMapping("/getChannelingByChannelingMidwife/{channeling_midwife}")//Get channeling by midwife    -----used------
        public List<VisitChannelingdto> getChannelingByChannelingMidwife(@Valid @PathVariable String channeling_midwife) {
            return visitChannelingService.getChannelingByChannelingDoctor(channeling_midwife);
        }

        @GetMapping("/getChannelingByChannelingDateMidwife/{channeling_datetime}/{channeling_midwife}")//Get channeling by date and midwife   -----used------
        public List<VisitChannelingdto> getChannelingByChannelingDateMidwife(@Valid @PathVariable LocalDate channeling_datetime, @Valid @PathVariable String channeling_midwife) {
            return visitChannelingService.getChannelingByChannelingDateDoctor(channeling_datetime, channeling_midwife);
        }


        @GetMapping("/getLatestChannelingIdForUser/{nic}")//get latest channeling for user by nic   -----used------
        public ResponseEntity<VisitChannelingdto> getLatestChannelingIdForUser(@Valid @PathVariable String nic) {
            VisitChannelingdto latestChanneling = visitChannelingService.getLatestChannelingIdForUser(nic);
            if (latestChanneling != null) {
                return ResponseEntity.ok(latestChanneling);
            } else {
                return null;
            }
        }

        @GetMapping("/getLatestChannelingId/{channeling_midwife_date}/{channeling_midwife}")// Get latest channeling by midwife date and midwife   -----used------
        public ResponseEntity<? extends Object> getLatestChannelingId(@Valid @PathVariable LocalDate channeling_midwife_date, @Valid @PathVariable String channeling_midwife) {
            VisitChannelingdto latestChanneling = visitChannelingService.getLatestChannelingId(channeling_midwife_date, channeling_midwife);

            if (latestChanneling != null) {
                return ResponseEntity.ok(latestChanneling);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @GetMapping("/countChannelingRecords/{midwife_id}/{appointmentDate}")//Count records  -----used------
        public ResponseEntity<?> countRecords(
                @Valid @PathVariable Long midwife_id,
                @Valid @PathVariable LocalDate appointmentDate) {
            List<VisitChanneling> count = visitChannelingRepository.countByDoctor_idAndAppointmentDate(midwife_id, appointmentDate);
            return ResponseEntity.ok(count != null ? count : 0);
        }

        @PostMapping("/schedules")//Save Channeling   -----used------
        public ResponseEntity<Responsedto> scheduleChanneling(@Valid @RequestBody VisitChannelingdto visitChannelingdto) {
            Responsedto response = visitChannelingService.processChanneling(visitChannelingdto);

            if (response.isSuccess()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        @PutMapping("/updateChanneling/{cha_id}")//Update Channeling By id   -----used------
        public VisitChannelingdto updateChanneling(@Valid @PathVariable Long cha_id, @Valid  @RequestBody VisitChannelingdto visitChannelingdto){
            return visitChannelingService.updateChanneling(cha_id,visitChannelingdto);
        }

    @PutMapping("/update/{cha_id}")//Upadte Channeling By id  -----used------
    public ResponseEntity<Responsedto> updateChannelingById(
            @PathVariable Long cha_id,
            @RequestBody VisitChannelingdto visitChannelingdto) {

        Responsedto response = visitChannelingService.updateChannelingById(cha_id, visitChannelingdto);

        // Return appropriate response based on the result
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    }

