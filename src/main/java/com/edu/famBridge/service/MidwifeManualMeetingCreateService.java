package com.edu.famBridge.service;

import com.edu.famBridge.dto.MidwifeManualMeetingCreatedto;
import com.edu.famBridge.entity.MidwifeManualMeetingCreate;
import jakarta.validation.Valid;

public interface MidwifeManualMeetingCreateService {
    MidwifeManualMeetingCreate addOrUpdateMeeting(MidwifeManualMeetingCreatedto midwifeManualMeetingCreatedto);
    MidwifeManualMeetingCreatedto updateChannelingForMeeting( Long id,  String meetingLink);
    MidwifeManualMeetingCreatedto getChannelingByID(@Valid Long id);
}
