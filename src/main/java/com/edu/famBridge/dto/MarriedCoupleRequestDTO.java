package com.edu.famBridge.dto;

import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MarriedCoupleRequestDTO {
    private long requestId;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String province;
    private String city;
    private String district;
    private String mohArea;
    private String gramaNiladhariDivision;
    private String email;
    private String contactNo;
    private LocalDate requestDate;
    private LocalDate appointmentDate;
    private String requestStatus;

    @JsonIgnore
    private byte[] marriageProof;
    private int statusCode;
    private String error;
    private String message;
    private MarriedCoupleRequest marriedCoupleRequest;
    private List<MarriedCoupleRequest> marriedCoupleRequestList;
}
