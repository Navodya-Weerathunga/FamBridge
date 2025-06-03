package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MidwifeManualMeetingCreatedto;
import com.edu.famBridge.entity.MidwifeManualMeetingCreate;
import com.edu.famBridge.repository.MidwifeManualMeetingCreateRepository;
import com.edu.famBridge.service.MidwifeManualMeetingCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "famBridge/MidwifeManualMeetingCreate")
@CrossOrigin(origins = "http://localhost:3000")
public class MidwifeManualMeetingCreateController {
    @Autowired
    private MidwifeManualMeetingCreateService midwifeManualMeetingCreateService;
    @Autowired
    private MidwifeManualMeetingCreateRepository midwifeManualMeetingCreateRepository;

    // Add or Update appointment   -----used------
    @PostMapping("/addOrUpdateMeeting")
    public ResponseEntity<MidwifeManualMeetingCreate> addOrUpdateMeeting(@RequestBody MidwifeManualMeetingCreatedto midwifeManualMeetingCreatedto) {
        MidwifeManualMeetingCreate savedMeeting = midwifeManualMeetingCreateService.addOrUpdateMeeting(midwifeManualMeetingCreatedto);
        return ResponseEntity.ok(savedMeeting);
    }
    //Get channeling by id    -----used------
    @GetMapping("/getChannelingById/{id}")//ok
    public MidwifeManualMeetingCreatedto getChannelingById(@Valid @PathVariable Long id) {
        return midwifeManualMeetingCreateService.getChannelingByID(id);
    }
}
