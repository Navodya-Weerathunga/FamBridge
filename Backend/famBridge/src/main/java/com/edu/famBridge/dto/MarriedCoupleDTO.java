package com.edu.famBridge.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MarriedCoupleDTO {
    private long marriedCoupleId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String nic;
    private LocalDate dob;
    private String phmArea;
    private LocalDate registeredDate;
    private String occupation;
    private String educationLevel;
    private String password;
    private Integer loginCount;
    private Integer login;
    private String confirmPassword;
    private String assignMidwife;

    private String husbandFirstName;
    private String husbandLastName;
    private String husbandNic;
    private String husbandContactNo;
    private LocalDate husbandDob;
    private String husbandOccupation;
    private String husbandEducationLevel;

    private LocalDate marriedDate;
    private String marriageCertificateNo;
    private String marriagePlace;

    private int statusCode;
    private String error;
    private String message;
}
