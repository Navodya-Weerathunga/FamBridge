package com.edu.famBridge.serviceImpl;


import com.edu.famBridge.dto.VisitReminderdto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitReminder;
import com.edu.famBridge.repository.VisitReminderRepository;
import com.edu.famBridge.service.VisitReminderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VisitReminderServiceImpl implements VisitReminderService {
    @Autowired
    private final VisitReminderRepository visitReminderRepository;
    @Autowired
    private ReminderEmailServiceImpl reminderEmailServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

// create reminder
    @Override
    public VisitReminder addOrUpdateReminder(VisitReminderdto visitReminderdto) {
        VisitReminder reminder = new VisitReminder();
        reminder .setUsers(visitReminderdto.getUsers());
        reminder .setArea(visitReminderdto.getArea());
        reminder .setAllocation(visitReminderdto.getAllocation());
        reminder .setEndTime(visitReminderdto.getEndTime());
        reminder .setStartTime(visitReminderdto.getStartTime());
        reminder .setVisitingDate(visitReminderdto.getVisitingDate());
        reminder .setMidwifeId(visitReminderdto.getMidwifeId());
        reminder .setMidwifeName(visitReminderdto.getMidwifeName());
        reminder. setDate(visitReminderdto.getDate());


        VisitReminder savedReminder = visitReminderRepository.save(reminder);

        reminderEmailServiceImpl.sendReminderEmailService(visitReminderdto.getVisitingDate(), visitReminderdto.getStartTime(), visitReminderdto.getEndTime(),visitReminderdto.getArea(),visitReminderdto.getUsers());
        return savedReminder;

    }
    @Transactional
    @Override// Delete reminder
    public void deleteReminder(Long id) {

        VisitReminder card= visitReminderRepository.findByIDOfreminder(id);

        reminderEmailServiceImpl.sendReminderEmailDeleteService(card.getVisitingDate(), card.getStartTime(), card.getEndTime(),card.getArea(),card.getUsers());
        visitReminderRepository.deleteById(id);
    }

    @Override// Get all reminders
    public List<VisitReminder> getAllReminders() {
        return visitReminderRepository.findAll();
    }

}
