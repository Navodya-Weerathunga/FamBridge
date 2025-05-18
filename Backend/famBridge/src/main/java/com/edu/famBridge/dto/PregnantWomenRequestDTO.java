package com.edu.famBridge.dto;

import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PregnantWomenRequestDTO {

    private LocalDate requestDate;
    private Long marriedCoupleId;

    @JsonIgnore
    private byte[] pregnancyProof;

    private String status;

    private String firstName;
    private String email;
    private String assignMidwife;
    private String contactNo;
    private String nic;
    private String phmArea;
    private LocalDate appointmentDate;

    private int statusCode;
    private String error;
    private String message;

}
