package com.edu.famBridge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Midwife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ElementCollection
    private List<String> workingArea;
    private String midwifeType;
    private String qualifications;

}
