package com.edu.famBridge.controller;

import com.edu.famBridge.dto.VisitReminderdto;
import com.edu.famBridge.entity.VisitReminder;
import com.edu.famBridge.repository.VisitReminderRepository;
import com.edu.famBridge.service.VisitReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "famBridge/VisitReminder")
@CrossOrigin(origins = "http://localhost:3000")
public class VisitReminderController {
    @Autowired
    private VisitReminderService visitReminderService;
    @Autowired
    private VisitReminderRepository visitReminderRepository;

    // Create Reminder  -----used------
    @PostMapping("/add")
    public ResponseEntity<VisitReminder> addReminder(@RequestBody VisitReminderdto visitReminderdto) {
        VisitReminder reminder= visitReminderService.addOrUpdateReminder(visitReminderdto);
        return ResponseEntity.ok(reminder);
    }

    //Delete reminder by id   -----used------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReminder(@PathVariable Long id) {
        visitReminderService.deleteReminder(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }
    //Get all reminders   -----used------
    @GetMapping("/all")
    public List<VisitReminder> getAllReminders() {
        return visitReminderService.getAllReminders();
    }


}
