package com.edu.famBridge.service;

import com.edu.famBridge.dto.Responsedto;
import com.edu.famBridge.dto.VisitChannelingdto;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface VisitChannelingService {
    VisitChannelingdto saveChanneling(@Valid VisitChannelingdto visitChannelingdto);
    VisitChannelingdto updateVaccineChanneling(@Valid VisitChannelingdto visitChannelingdto);
    List<VisitChannelingdto> getAllChannelings();
    VisitChannelingdto getChannelingByChannelingID(@Valid Long cha_id);
    List<VisitChannelingdto> getChannelingByChannelingDate(@Valid String channeling_datetime);
    List<VisitChannelingdto> getChannelingByChannelingDoctor(@Valid String channeling_midwife);
    List<VisitChannelingdto> getChannelingByChannelingDateDoctor(@Valid LocalDate channeling_datetime, @Valid String channeling_midwife);
    VisitChannelingdto getLatestChannelingIdForUser(@Valid String nic);
    VisitChannelingdto getLatestChannelingId(@Valid LocalDate channeling_midwife_date, @Valid String channeling_midwife);
    Responsedto processChanneling(VisitChannelingdto visitChannelingdto);
    boolean deleteChannelingByChannelingID( @Valid Long cha_id);
    VisitChannelingdto changeChannelingAdmin( @Valid Long cha_id, VisitChannelingdto visitChannelingdto);
    VisitChannelingdto updateChanneling(@Valid Long cha_id ,@Valid VisitChannelingdto visitChannelingdto);
    Responsedto updateChannelingById(Long cha_id, VisitChannelingdto visitChannelingdto);
    VisitChannelingdto updateVaccineChannelingForMeeting(@Valid Long cha_id, @Valid String meetingLink);
}
