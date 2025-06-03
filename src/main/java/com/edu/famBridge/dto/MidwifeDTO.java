package com.edu.famBridge.dto;


import com.edu.famBridge.entity.Midwife;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MidwifeDTO {

    private long midwifeId;
    private String fullName;
    private String nic;
    private LocalDate dob;
    private String province;
    private String district;
    private String address;
    private String contactNumber;
    private String email;
    private String medicalCouncilNumber;
    private String password;
    private LocalDate registeredDate;
    private String mohArea;
    private List <String> workingArea;
    private String midwifeType;
    private String qualifications;
    private int statusCode;
    private String error;
    private String message;
    private String confirmPassword;
    private boolean isLogin;
    private Midwife midwife;
    private List<Midwife> midwifeList;
}
