package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.repository.MidwifeScheduleRepository;
import com.edu.famBridge.service.MidwifeScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "famBridge/MidwifeSchedule")
@CrossOrigin(origins = "http://localhost:3000")
public class MidwifeScheduleController {

        @Autowired
        private MidwifeScheduleService midwifeScheduleService;
        @Autowired
        private MidwifeScheduleRepository midwifeScheduleRepository;

        // Add or Update appointment    -----used------
        @PostMapping("/add")
        public ResponseEntity<MidwifeSchedule> addAppointment(@RequestBody MidwifeScheduledto midwifeScheduledto) {
            MidwifeSchedule savedAppointment = midwifeScheduleService.addOrUpdateAppointment(midwifeScheduledto);
            return ResponseEntity.ok(savedAppointment);
        }

        // Get all appointments   -----used------
        @GetMapping("/all")
        public List<MidwifeSchedule> getAllAppointments() {
            return midwifeScheduleService.getAllAppointments();
        }


        // Delete an appointment   -----used------
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
            midwifeScheduleService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment deleted successfully");
        }
        // Get appointment by id   -----used------
        @GetMapping("/getAppointmentById/{id}")//ok
        public MidwifeScheduledto getAppointmentById(@Valid @PathVariable Long id) {
            return midwifeScheduleService.getAppointmentByID(id);
        }
        //Update appointment by id   -----used------
        @PutMapping("/updateAppointmentByID/{id}")
        public MidwifeScheduledto updateAppointmentByID(@Valid @PathVariable Long id,@Valid  @RequestBody MidwifeScheduledto midwifeScheduledto){
            return midwifeScheduleService.updateAppointmentByID(id,midwifeScheduledto);
        }

        //Get latest record according to midwife id    -----used------
        @GetMapping("/getLatestAppointmentId/{midwifeId}")
        public ResponseEntity<MidwifeScheduledto> getLatestAppointmentId(@Valid @PathVariable String midwifeId) {
            MidwifeScheduledto latestappointment = midwifeScheduleService.getLatestAppointmentId(midwifeId);
            if (latestappointment != null) {
                return ResponseEntity.ok(latestappointment);
            } else {
                return null;
            }
        }


        //Get midwife appointments by midwifename   -----used------
        @GetMapping( "/getMidwifeByMidwifename/{midwifeName}")//ok
        public List<MidwifeScheduledto> getMidwifeByMidwifename(@Valid @PathVariable String midwifeName){
            return midwifeScheduleService.getMidwifeByMidwifename(midwifeName);}

        //Get appointment by date and midwife name  -----used------
        @GetMapping("/getMidwifeID/{availableDate}/{midwifeName}")//ok
        public MidwifeScheduledto getMidwifeID(@Valid @PathVariable LocalDate availableDate, @Valid @PathVariable String midwifeName) {
            return midwifeScheduleService.getMidwifeID(availableDate,midwifeName);
        }

    //Get appointment by midwife id and area  -----used------
    @GetMapping("/getMidwifeArea/{midwifeId}/{area}")//ok
    public List<MidwifeScheduledto> getMidwifeArea(@Valid @PathVariable String midwifeId, @Valid @PathVariable String area) {
        return midwifeScheduleService.getMidwifeArea(midwifeId,area);
    }

    //Get appointment by midwife name and id   -----used------
        @GetMapping("/getMidwife/{midwifeName}/{id}")//ok
        public  MidwifeScheduledto MidwifeScheduledtogetMidwife(@Valid @PathVariable String midwifeName, @Valid @PathVariable Long id) {
            return midwifeScheduleService.getMidwife(midwifeName,id);
        }

    // Get appointment by date and area  -----used------
    @GetMapping("/getMidwifeDate/{availableDate}/{area}")//ok
    public MidwifeScheduledto getMidwifeDate(@Valid @PathVariable LocalDate availableDate, @Valid @PathVariable String area) {
        return midwifeScheduleService.getMidwifeDate(availableDate,area);
    }

    // Get appointment by  area  -----used------
    @GetMapping("/getMidwifeDate/{area}")//ok
    public List<MidwifeScheduledto> getMidwifeDateByArea( @Valid @PathVariable String area) {
        return midwifeScheduleService.getMidwifeDateByArea(area);
    }
    //------------------------------------------------------------------------------------

        //Get allocation by id and date
        @GetMapping("/midwifeAllocation/{id}/{availableDate}")
        public ResponseEntity<?> getMidwifeAllocation(
                @Valid @PathVariable Long id,
                @Valid @PathVariable LocalDate availableDate) {
            int allocation = midwifeScheduleRepository.getAllocationAppoinmentIdAndDate(id,availableDate);
            return ResponseEntity.ok(allocation);
        }


    @GetMapping("/getAllAppointmentsVisits")
    public List<MidwifeSchedule> getAllAppointmentsVisits() {
        return midwifeScheduleService.getAllAppointmentsVisits();
    }

    @GetMapping("/getmidwifes")//ok
    public List<MidwifeScheduledto> getMidwifes() {

        return midwifeScheduleService.getAllMidwifes();
    }
    // Search appointments by date
    @GetMapping("/search")
    public List<MidwifeSchedule> getAppointmentsByDate(@RequestParam("date") String availableDate) {
        LocalDate availableDates = LocalDate.parse(availableDate);
        return midwifeScheduleService.getAppointmentsByDate(availableDates);
    }

    //get midwife allocation by id
    @GetMapping("/getMidwifeAllocation/{id}")
    public ResponseEntity<MidwifeScheduledto> getMidwifeAllocation(@Valid @PathVariable Long id) {

        MidwifeScheduledto midwife = midwifeScheduleService.findByDid(id);
        return midwife != null ? ResponseEntity.ok(midwife) : ResponseEntity.notFound().build();
    }

    }


