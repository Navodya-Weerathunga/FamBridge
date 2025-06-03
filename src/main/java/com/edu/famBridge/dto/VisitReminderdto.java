package com.edu.famBridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitReminderdto {
    private Long id;
    private String midwifeId;
    private String midwifeName;
    private LocalDate visitingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int allocation;
    private String area;
    private String users;
    private LocalDate date;
}
