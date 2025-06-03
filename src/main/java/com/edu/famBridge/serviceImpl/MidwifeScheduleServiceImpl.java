package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.dto.Responsedto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.entity.VisitChanneling;
import com.edu.famBridge.repository.MidwifeScheduleRepository;
import com.edu.famBridge.service.MidwifeScheduleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MidwifeScheduleServiceImpl implements MidwifeScheduleService {

        @Autowired
        private final MidwifeScheduleRepository midwifeScheduleRepository ;
        @Autowired
        private ModelMapper modelMapper;


    @Override
    public MidwifeSchedule addOrUpdateAppointment(MidwifeScheduledto midwifeScheduledto) {
        // Null checks
        if (midwifeScheduledto.getMidwifeId() == null ||
                midwifeScheduledto.getMidwifeName() == null ||
                midwifeScheduledto.getAvailableDate() == null ||
                midwifeScheduledto.getStartTime() == null ||
                midwifeScheduledto.getEndTime() == null ||
                midwifeScheduledto.getAppointmentsPerDay() == 0 ||
                midwifeScheduledto.getWorkType() == null ||
                midwifeScheduledto.getArea() == null) {
            throw new IllegalArgumentException("All fields are required and cannot be null");
        }

        // Time validation
        if (midwifeScheduledto.getEndTime().isBefore(midwifeScheduledto.getStartTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        List<MidwifeSchedule> existingAppointments = midwifeScheduleRepository
                .findByDateid(midwifeScheduledto.getAvailableDate(), midwifeScheduledto.getMidwifeName());

        if (existingAppointments != null && !existingAppointments.isEmpty()) {
            throw new IllegalArgumentException("Duplicates cannot enter");
        }
        MidwifeSchedule appointment = new MidwifeSchedule();
        appointment.setMidwifeId(midwifeScheduledto.getMidwifeId());
        appointment.setMidwifeName(midwifeScheduledto.getMidwifeName());
        appointment.setAvailableDate(midwifeScheduledto.getAvailableDate());
        appointment.setStartTime(midwifeScheduledto.getStartTime());
        appointment.setEndTime(midwifeScheduledto.getEndTime());
        appointment.setAppointmentsPerDay(midwifeScheduledto.getAppointmentsPerDay());
        appointment.setWorkType(midwifeScheduledto.getWorkType());
        appointment.setArea(midwifeScheduledto.getArea());

        return midwifeScheduleRepository.save(appointment);
    }


    @Override// Get all appointments
        public List<MidwifeSchedule > getAllAppointments() {
            return midwifeScheduleRepository.findAll();
        }



        @Override// Get appointment by doctor ID and available date
        public Optional<MidwifeSchedule > getAppointmentByDoctorAndDate(Long id, LocalDate availableDate) {
            return midwifeScheduleRepository.findById(id);
        }

        @Override// Delete appointment
        public void deleteAppointment(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Appointment ID cannot be null");
            }

            if (!midwifeScheduleRepository.existsById(id)) {
                throw new EntityNotFoundException("Appointment with ID " + id + " does not exist");
            }

            midwifeScheduleRepository.deleteById(id);
        }

    @Override//get appointment by id
        public MidwifeScheduledto getAppointmentByID(@Valid Long id){
            MidwifeSchedule  appointment=midwifeScheduleRepository.getappointmentID(id);
            return modelMapper.map(appointment,MidwifeScheduledto.class);
        }
    @Override
    public MidwifeScheduledto updateAppointmentByID(@Valid Long id, @Valid MidwifeScheduledto midwifeScheduledto) {
        if (midwifeScheduledto == null) {
            throw new NullPointerException("MidwifeScheduledto must not be null");
        }

        // Fetch the existing appointment
        Optional<MidwifeSchedule> optionalAppointment = midwifeScheduleRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new NoSuchElementException("Appointment with ID " + id + " not found");
        }

        MidwifeSchedule existingAppointment = optionalAppointment.get();

        if (midwifeScheduledto.getAppointmentsPerDay() < 0) {
            throw new IllegalArgumentException("Appointments per day cannot be negative");
        }
        if (midwifeScheduledto.getEndTime().isBefore(midwifeScheduledto.getStartTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
        // Update the necessary fields
   // Example: updating the name
        existingAppointment.setMidwifeId(midwifeScheduledto.getMidwifeId());
        existingAppointment.setMidwifeName(midwifeScheduledto.getMidwifeName());
        existingAppointment.setAvailableDate(midwifeScheduledto.getAvailableDate());
        existingAppointment.setStartTime(midwifeScheduledto.getStartTime());
        existingAppointment.setEndTime(midwifeScheduledto.getEndTime());
        existingAppointment.setAppointmentsPerDay(midwifeScheduledto.getAppointmentsPerDay());
        existingAppointment.setWorkType(midwifeScheduledto.getWorkType());
        existingAppointment.setArea(midwifeScheduledto.getArea()); // Example: updating the time

        // Save the updated appointment
        midwifeScheduleRepository.save(existingAppointment);

        // Return the updated DTO (you can map it back if needed)
        return modelMapper.map(existingAppointment, MidwifeScheduledto.class);
    }


    @Override// Get latest appointment according to midwife id
        public MidwifeScheduledto getLatestAppointmentId(@Valid String midwifeId) {
            Long latestappointmentId =midwifeScheduleRepository.findLatestOrderIdByIndexNo(midwifeId);
            if (latestappointmentId != null) {
                Optional<MidwifeSchedule > channelingOptional = midwifeScheduleRepository.findById(latestappointmentId);
                if (channelingOptional.isPresent()) {
                    return modelMapper.map(channelingOptional.get(), MidwifeScheduledto.class);
                }
            }
            return null;
        }



        @Override//Get appointments by midwife name
        public List<MidwifeScheduledto> getMidwifeByMidwifename(@Valid String midwifeName){
            List<MidwifeSchedule > doctorList=midwifeScheduleRepository.getAppointmentsByMidwifename(midwifeName);
            return modelMapper.map(doctorList,new TypeToken<List<MidwifeScheduledto>>(){}.getType());
        }

        @Override// Get appointment by date and midwife name
        public MidwifeScheduledto getMidwifeID(@Valid LocalDate availableDate, @Valid String midwifeName){
            MidwifeSchedule  channeling=midwifeScheduleRepository.getMidwifeID(availableDate,midwifeName);
            return modelMapper.map(channeling, MidwifeScheduledto.class);
        }

    @Override// Get appointment by date and area
    public MidwifeScheduledto getMidwifeDate(@Valid LocalDate availableDate, @Valid String area){
        MidwifeSchedule  channeling=midwifeScheduleRepository.getMidwifeDate(availableDate,area);
        return modelMapper.map(channeling, MidwifeScheduledto.class);
    }
    @Override //Get appointments by midwife id and area
    public List<MidwifeScheduledto> getMidwifeArea(@Valid String midwifeId, @Valid String area){
        List<MidwifeSchedule> channeling=midwifeScheduleRepository.getMidwifeArea(midwifeId,area);
        return modelMapper.map(channeling, new TypeToken<List<MidwifeScheduledto>>(){}.getType());
    }

        @Override // Get appointment by midwife name and id
        public MidwifeScheduledto getMidwife(@Valid String midwifeName, @Valid Long id){
            MidwifeSchedule  channeling=midwifeScheduleRepository.getMidwife(midwifeName,id);
            return modelMapper.map(channeling, MidwifeScheduledto.class);
        }

        //_________________________________________________________________________

    @Override //Get appointment by id
    public MidwifeScheduledto findByDid(Long id) {

        return midwifeScheduleRepository.findByDid(id);
    }

    @Override// Get all appointments
    public List<MidwifeSchedule > getAllAppointmentsVisits() {
        return midwifeScheduleRepository.findAllVisits();
    }



    @Override// Get appointments by available date
    public List<MidwifeSchedule > getAppointmentsByDate(LocalDate availableDate) {
        return midwifeScheduleRepository.findByAvailableDate(availableDate);
    }

    @Override// Get all midwifes appointments
    public List<MidwifeScheduledto> getAllMidwifes(){
        List<MidwifeSchedule > doctorList=midwifeScheduleRepository.findAll();
        return modelMapper.map(doctorList,new TypeToken<List<MidwifeScheduledto>>(){}.getType());

    }



    @Override //Get appointments by midwife id and area
    public List<MidwifeScheduledto> getMidwifeDateByArea( @Valid String area){
        List<MidwifeSchedule> channeling=midwifeScheduleRepository.getMidwifeDateByArea(area);
        return modelMapper.map(channeling, new TypeToken<List<MidwifeScheduledto>>(){}.getType());
    }
}

