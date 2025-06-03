package com.edu.famBridge.service;

import com.edu.famBridge.dto.VisitReminderdto;
import com.edu.famBridge.entity.VisitReminder;

import java.util.List;

public interface VisitReminderService {
    VisitReminder addOrUpdateReminder(VisitReminderdto visitReminderdto);
    void deleteReminder(Long id);
    List<VisitReminder> getAllReminders();
}
