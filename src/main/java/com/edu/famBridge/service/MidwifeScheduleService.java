package com.edu.famBridge.service;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.entity.MidwifeSchedule;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MidwifeScheduleService {
    List<MidwifeScheduledto> getAllMidwifes();
    List<MidwifeScheduledto> getMidwifeByMidwifename(@Valid String midwifeName);
    MidwifeSchedule addOrUpdateAppointment(MidwifeScheduledto midwifeScheduledto);
    List<MidwifeSchedule> getAllAppointments();
    List<MidwifeSchedule> getAppointmentsByDate(LocalDate availableDate);
    Optional<MidwifeSchedule> getAppointmentByDoctorAndDate(Long id, LocalDate availableDate);
    void deleteAppointment(Long id);
    MidwifeScheduledto getAppointmentByID(@Valid Long id);
    MidwifeScheduledto updateAppointmentByID(@Valid Long id ,@Valid MidwifeScheduledto midwifeScheduledto);
    MidwifeScheduledto getLatestAppointmentId(@Valid String midwifeId);
    MidwifeScheduledto getMidwifeID(@Valid LocalDate availableDate, @Valid String midwifeName);
    MidwifeScheduledto getMidwife(@Valid String midwifeName, @Valid Long id);
    MidwifeScheduledto findByDid(Long id);
    List<MidwifeSchedule > getAllAppointmentsVisits();
    MidwifeScheduledto getMidwifeDate(@Valid LocalDate availableDate, @Valid String area);
    List<MidwifeScheduledto> getMidwifeArea(@Valid String midwifeId, @Valid String area);
    List<MidwifeScheduledto> getMidwifeDateByArea( @Valid String area);
}
