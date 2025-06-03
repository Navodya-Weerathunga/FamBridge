package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.Responsedto;
import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitChanneling;
import com.edu.famBridge.repository.MidwifeScheduleRepository;
import com.edu.famBridge.repository.VisitChannelingRepository;
import com.edu.famBridge.service.VisitChannelingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitChannelingServiceImpl implements VisitChannelingService {

        @Autowired
        private final ModelMapper modelMapper;
        @Autowired
        private MidwifeScheduleRepository midwifeScheduleRepository;

        @Autowired
        private VisitChannelingRepository visitChannelingRepository;



        @Override
        public VisitChannelingdto saveChanneling(@Valid VisitChannelingdto visitChannelingdto){
            visitChannelingRepository.save(modelMapper.map(visitChannelingdto, VisitChanneling.class));
            return visitChannelingdto;
        }

        @Override
        public VisitChannelingdto updateVaccineChanneling(@Valid VisitChannelingdto visitChannelingdto){
            visitChannelingRepository.save(modelMapper.map(visitChannelingdto, VisitChanneling.class));
            return visitChannelingdto;
        }

        @Override//ok
        public List<VisitChannelingdto> getAllChannelings(){
            List<VisitChanneling>vaccinechannelingList=visitChannelingRepository.findAll();
            return modelMapper.map(vaccinechannelingList,new TypeToken<List<VisitChannelingdto>>(){}.getType());

        }


        @Override//ok
        public VisitChannelingdto getChannelingByChannelingID(@Valid Long cha_id){
            VisitChanneling channeling=visitChannelingRepository.getChannelingByChannelingID(cha_id);
            return modelMapper.map(channeling,VisitChannelingdto.class);
        }

        @Override//ok
        public List<VisitChannelingdto> getChannelingByChannelingDate(@Valid String channeling_datetime){
            List<VisitChanneling> channelingList=visitChannelingRepository.getChannelingByChannelingDate(channeling_datetime);
            return modelMapper.map(channelingList, new TypeToken<List<VisitChannelingdto>>() {}.getType());
        }

        @Override//ok
        public List<VisitChannelingdto> getChannelingByChannelingDoctor(@Valid String channeling_midwife){
            List<VisitChanneling>  channelingList=visitChannelingRepository.getChannelingByChannelingMidwife(channeling_midwife);
            return modelMapper.map(channelingList, new TypeToken<List<VisitChannelingdto>>() {}.getType());
        }

        @Override//ok
        public List<VisitChannelingdto> getChannelingByChannelingDateDoctor(@Valid LocalDate channeling_datetime, @Valid String channeling_midwife) {
            List<VisitChanneling> channelingList = visitChannelingRepository.getChannelingByChannelingDateMidwife(channeling_datetime, channeling_midwife);

            return modelMapper.map(channelingList, new TypeToken<List<VisitChannelingdto>>() {}.getType());
        }

        @Override//ok
        public VisitChannelingdto getLatestChannelingIdForUser(@Valid String nic) {
            Long latestChannelingId =visitChannelingRepository.findLatestOrderIdByIndexNo(nic);
            if (latestChannelingId != null) {
                Optional<VisitChanneling> channelingOptional = visitChannelingRepository.findById(latestChannelingId);
                if (channelingOptional.isPresent()) {
                    return modelMapper.map(channelingOptional.get(), VisitChannelingdto.class);
                }
            }
            return null;
        }

        @Override//ok
        public VisitChannelingdto getLatestChannelingId(@Valid LocalDate channeling_midwife_date, @Valid String channeling_midwife){
            VisitChanneling channeling=visitChannelingRepository.findLatestOrderId(channeling_midwife_date,channeling_midwife);
            if (channeling != null) {
                return modelMapper.map(channeling, VisitChannelingdto.class);
            }
            return null;
        }

        @Override//ok
        @Transactional
        public Responsedto processChanneling(VisitChannelingdto visitChannelingdto) {

            Integer chdr = visitChannelingdto.getChanneling_number();

            String warning = "";
            String vaccineStatusMessage = " ";

            List<VisitChanneling> existingAppointments = visitChannelingRepository
                    .findByDateNic(visitChannelingdto.getChanneling_midwife_date(), visitChannelingdto.getNic());

            if (existingAppointments != null && !existingAppointments.isEmpty()) {
                return new Responsedto("You have already set the meeting request for this date.", false);
            }

            List<MidwifeSchedule> doctors = midwifeScheduleRepository.findByName(visitChannelingdto.getChanneling_midwife());
            if (doctors.isEmpty()) {
                return new Responsedto("Midwife not found with name: " + visitChannelingdto.getChanneling_midwife(), false);
            }

            MidwifeSchedule allocation = midwifeScheduleRepository.findByMidwifeIdAndAllocationDate(
                    visitChannelingdto.getMidwife_id(),
                    visitChannelingdto.getChanneling_midwife_date()
            );
            if (allocation == null) {
                return new Responsedto(
                        "No allocation found for this date: " + visitChannelingdto.getMidwife_id() + " " + visitChannelingdto.getChanneling_midwife_date(),
                        false
                );
            }


            List<VisitChanneling> currentAppointments = visitChannelingRepository
                    .countByDoctor_idAndAppointmentDate(visitChannelingdto.getMidwife_id(), visitChannelingdto.getChanneling_midwife_date());
            Integer currentAppointmentCount = currentAppointments.size();
            if (currentAppointmentCount >= allocation.getAppointmentsPerDay()) {
                return new Responsedto("The Midwife has reached the maximum number of patients for this date.", false);
            }


            int appointmentNumber = currentAppointmentCount + 1;
            visitChannelingdto.setChannel_number(appointmentNumber);


            VisitChanneling appointmentEntity = new VisitChanneling();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(visitChannelingdto, appointmentEntity);
            visitChannelingRepository.save(appointmentEntity);

            return new Responsedto(warning  + vaccineStatusMessage + "\n" + "Appointment successfully scheduled.", true);
        }

        @Override
        @Transactional//ok
        public boolean deleteChannelingByChannelingID( @Valid Long cha_id){

//            String vaccinename= visitChannelingRepository.getVaccineName(cha_id);

            visitChannelingRepository.deleteById(cha_id);


            return true;
        }

    @Override
    @Transactional
    public Responsedto updateChannelingById(Long cha_id, VisitChannelingdto visitChannelingdto) {

        List<VisitChanneling> existingAppointments = visitChannelingRepository
                .findByDateNic(visitChannelingdto.getChanneling_midwife_date(), visitChannelingdto.getNic());

        if (existingAppointments != null && !existingAppointments.isEmpty()) {
            return new Responsedto("You have already set the meeting request for this date.", false);
        }
        // Retrieve the existing visit channeling by cha_id
        Optional<VisitChanneling> existingChannelingOpt = visitChannelingRepository.findById(cha_id);
        if (!existingChannelingOpt.isPresent()) {
            return new Responsedto("Channeling not found with ID: " + cha_id, false);
        }

        VisitChanneling existingChanneling = existingChannelingOpt.get();

        // Check if the midwife exists
        List<MidwifeSchedule> doctors = midwifeScheduleRepository.findByName(visitChannelingdto.getChanneling_midwife());
        if (doctors.isEmpty()) {
            return new Responsedto("Midwife not found with name: " + visitChannelingdto.getChanneling_midwife(), false);
        }

        MidwifeSchedule allocation = midwifeScheduleRepository.findByMidwifeIdAndAllocationDate(
                visitChannelingdto.getMidwife_id(),
                visitChannelingdto.getChanneling_midwife_date()
        );
        if (allocation == null) {
            return new Responsedto(
                    "No allocation found for this date: " + visitChannelingdto.getMidwife_id() + " " + visitChannelingdto.getChanneling_midwife_date(),
                    false
            );
        }

        // Get the current count of appointments for the given midwife and date
        List<VisitChanneling> currentAppointments = visitChannelingRepository
                .countByDoctor_idAndAppointmentDate(visitChannelingdto.getMidwife_id(), visitChannelingdto.getChanneling_midwife_date());
        Integer currentAppointmentCount = currentAppointments.size();

        // Calculate the new channel number
        int newChannelNumber = currentAppointmentCount + 1;

        // If the appointment count is already at the max, return an error
        if (currentAppointmentCount >= allocation.getAppointmentsPerDay()) {
            return new Responsedto("The Midwife has reached the maximum number of patients for this date.", false);
        }

        // Update the channel number and other fields in the existing channeling record
        existingChanneling.setChannel_number(newChannelNumber);
        existingChanneling.setChanneling_midwife(visitChannelingdto.getChanneling_midwife());
        existingChanneling.setChanneling_midwife_date(visitChannelingdto.getChanneling_midwife_date());
        existingChanneling.setMidwife_id(visitChannelingdto.getMidwife_id());
        existingChanneling.setChanneling_number(visitChannelingdto.getChanneling_number()); // Update other fields as needed

        // Save the updated visit channeling entity
        visitChannelingRepository.save(existingChanneling);

        return new Responsedto("Channeling updated successfully with new channel number: " + newChannelNumber, true);
    }




    @Override
        @Transactional//ok
        public VisitChannelingdto changeChannelingAdmin( @Valid Long cha_id, VisitChannelingdto visitChannelingdto){


            VisitChanneling entity = modelMapper.map(visitChannelingdto, VisitChanneling.class);
            visitChannelingRepository.save(entity);

            return visitChannelingdto;
        }



        @Override
        public VisitChannelingdto updateChanneling(@Valid Long cha_id, @Valid VisitChannelingdto visitChannelingdto) {
            // Fetch existing record
            VisitChanneling existingChanneling = visitChannelingRepository.findById(cha_id)
                    .orElseThrow(() -> new RuntimeException("Channeling not found"));

            // Preserve meeting_link if not provided in request
            if (visitChannelingdto.getMeetingLink() == null) {
                visitChannelingdto.setMeetingLink(existingChanneling.getMeetingLink());
            }

            // Map DTO to entity and save
            VisitChanneling updatedChanneling = modelMapper.map(visitChannelingdto, VisitChanneling.class);
            updatedChanneling.setCha_id(cha_id); // Ensure ID remains the same

            visitChannelingRepository.save(updatedChanneling);

            return visitChannelingdto;
        }


    public VisitChannelingdto updateVaccineChannelingForMeeting(@Valid Long cha_id, @Valid String meetingLink) {
        // Fetch the existing VisitChanneling entity
        Optional<VisitChanneling> existingChannelingOpt = visitChannelingRepository.findById(cha_id);

        if (existingChannelingOpt.isPresent()) {
            VisitChanneling existingChanneling = existingChannelingOpt.get();

            // Update only the meeting link
            existingChanneling.setMeetingLink(meetingLink);

            // Save the updated entity
            visitChannelingRepository.save(existingChanneling);

            // Convert to DTO and return
            return modelMapper.map(existingChanneling, VisitChannelingdto.class);
        } else {
            throw new RuntimeException("VisitChanneling entity not found for ID: " + cha_id);
        }
    }

    }




