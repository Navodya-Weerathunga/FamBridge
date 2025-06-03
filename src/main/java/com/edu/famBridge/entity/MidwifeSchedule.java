package com.edu.famBridge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MidwifeSchedule {

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
