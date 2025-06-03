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
public class MidwifeManualMeetingCreatedto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String nic;
    private String email;
    private String midwife;
    private LocalDate date;
    private LocalTime starttime;
    private LocalDate today;
    private int meetingno;
    private String meetingLink;
}
