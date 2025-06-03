package com.edu.famBridge.dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MidwifeScheduledto {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        private String midwifeId;
        private String midwifeName;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDate availableDate;
        private int appointmentsPerDay;
        private String workType;
        private String area;


    }
