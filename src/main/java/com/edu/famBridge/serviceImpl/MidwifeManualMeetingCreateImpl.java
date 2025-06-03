package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MidwifeManualMeetingCreatedto;
import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.entity.MidwifeManualMeetingCreate;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitChanneling;
import com.edu.famBridge.repository.MidwifeManualMeetingCreateRepository;
import com.edu.famBridge.repository.MidwifeScheduleRepository;
import com.edu.famBridge.service.MidwifeManualMeetingCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MidwifeManualMeetingCreateImpl implements MidwifeManualMeetingCreateService {
    @Autowired
    private final MidwifeManualMeetingCreateRepository midwifeManualMeetingCreateRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override// Add or Update an meeting
    public MidwifeManualMeetingCreate addOrUpdateMeeting(MidwifeManualMeetingCreatedto midwifeManualMeetingCreatedto) {
        MidwifeManualMeetingCreate  meeting = new MidwifeManualMeetingCreate();
        meeting.setId(midwifeManualMeetingCreatedto.getId());
        meeting.setMidwife(midwifeManualMeetingCreatedto.getMidwife());
        meeting.setNic(midwifeManualMeetingCreatedto.getNic());
        meeting.setDate(midwifeManualMeetingCreatedto.getDate());
        meeting.setMeetingno(midwifeManualMeetingCreatedto.getMeetingno());
        meeting.setToday(midwifeManualMeetingCreatedto.getToday());
        meeting.setStarttime(midwifeManualMeetingCreatedto.getStarttime());
        meeting.setUsername(midwifeManualMeetingCreatedto.getUsername());
        meeting.setEmail(midwifeManualMeetingCreatedto.getEmail());
        meeting.setMeetingLink(midwifeManualMeetingCreatedto.getMeetingLink());

        return midwifeManualMeetingCreateRepository.save(meeting);
    }

    public MidwifeManualMeetingCreatedto updateChannelingForMeeting(@Valid Long chaId, @Valid String meetingLink) {
        // Fetch the existing VisitChanneling entity
        Optional<MidwifeManualMeetingCreate> existingChannelingOpt = midwifeManualMeetingCreateRepository.findById(chaId);

        if (existingChannelingOpt.isPresent()) {
            MidwifeManualMeetingCreate existingChanneling = existingChannelingOpt.get();

            // Update only the meeting link
            existingChanneling.setMeetingLink(meetingLink);

            // Save the updated entity
            midwifeManualMeetingCreateRepository.save(existingChanneling);

            // Convert to DTO and return
            return modelMapper.map(existingChanneling, MidwifeManualMeetingCreatedto.class);
        } else {
            throw new RuntimeException("VisitChanneling entity not found for ID: " + chaId);
        }
    }

    @Override
    public MidwifeManualMeetingCreatedto getChannelingByID(@Valid Long id){
        MidwifeManualMeetingCreate  channeling=midwifeManualMeetingCreateRepository.getchannelingID(id);
        return modelMapper.map(channeling,MidwifeManualMeetingCreatedto.class);
    }
}
