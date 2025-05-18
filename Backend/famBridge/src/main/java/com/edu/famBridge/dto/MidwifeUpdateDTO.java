package com.edu.famBridge.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MidwifeUpdateDTO {

    private String nic;
    private LocalDate dob;
    private String address;
    private String contactNumber;
    private String email;
    private String medicalCouncilNumber;
    private String message;
}
