package com.edu.famBridge.controller;

import com.edu.famBridge.dto.CancellationMessagedto;
import com.edu.famBridge.entity.CancelationMessage;
import com.edu.famBridge.repository.CancelationMessageRepository;
import com.edu.famBridge.service.CancellationMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "famBridge/CancellationMessage")
@CrossOrigin(origins = "http://localhost:3000")
public class CancellationMessageController {
    @Autowired
    private CancellationMessageService cancellationMessageService;
    @Autowired
    private CancelationMessageRepository cancelationMessageRepository;

    // Create Message   -----used------
    @PostMapping("/add")
    public ResponseEntity<CancelationMessage> addMessage(@RequestBody CancellationMessagedto cancellationMessagedto) {
        CancelationMessage savedMessage = cancellationMessageService.addOrUpdateMessage(cancellationMessagedto);
        return ResponseEntity.ok(savedMessage);
    }

    // Delete Message    -----used------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        cancellationMessageService.deleteMessage(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }

    // Update Message with relevant Id   -----used------
    @PutMapping("/updateMessageByID/{id}")
    public CancellationMessagedto updateMessageByID(@Valid @PathVariable Long id, @Valid  @RequestBody CancellationMessagedto cancellationMessagedto){
        return cancellationMessageService.updateMessageByID(id,cancellationMessagedto);
    }

    //Get Latest Cancelation Message according to user nic   -----used------
    @GetMapping("/getLatestCancelationIdForUser/{nic}")//ok
    public ResponseEntity<CancellationMessagedto> getLatestCancelationIdForUser(@Valid @PathVariable String nic) {
        CancellationMessagedto latestCancelation= cancellationMessageService.getLatestCancelationIdForUser(nic);
        if (latestCancelation != null) {
            return ResponseEntity.ok(latestCancelation);
        } else {
            return null;
        }
    }

    //Get cancelation by cancelation id   -----used------
    @GetMapping("/getCancelationByCancelationID/{id}")//ok
    public CancellationMessagedto getCancelationByCancelationID(@Valid @PathVariable Long id) {
        return cancellationMessageService.getCancelationByCancelationID(id);
    }

    //Get all records of Virtual cancelation  -----used------
    @GetMapping("/allVirtual")
    public List<CancelationMessage> getAllMessagesVirtual() {
        return cancellationMessageService.getAllMessagesVirtual();
    }

    //get all records of Physical cancelation.   -----used------
    @GetMapping("/allPhysical")
    public List<CancelationMessage> getAllMessagesPhysical() {
        return cancellationMessageService.getAllMessagesPhysical();
    }


}
