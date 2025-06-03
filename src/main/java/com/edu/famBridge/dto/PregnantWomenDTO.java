package com.edu.famBridge.dto;

import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.edu.famBridge.entity.PregnantWomen;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PregnantWomenDTO {
    private int pregnancyRecordNo;
    private LocalDate registeredDate;
    private Long requestId;

    private int statusCode;
    private String error;
    private String message;

    private String firstName;
    private String email;
    private String assignMidwife;
    private String contactNo;
    private String phmArea;
    private String nic;

}
