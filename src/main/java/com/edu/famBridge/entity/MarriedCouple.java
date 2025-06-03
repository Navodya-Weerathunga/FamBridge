package com.edu.famBridge.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MarriedCouple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long marriedCoupleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nic", referencedColumnName = "nic", nullable = false, unique = true)
    private MarriedCoupleRequest marriedCoupleRequest;

    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private LocalDate dob;
    private String phmArea;
    private LocalDate registeredDate;
    private String occupation;
    private String educationLevel;
    private String password;
    private Integer loginCount;
    private String assignMidwife;
    private String gramaNiladhariDivision;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marriedCouple", orphanRemoval = true)
    @JsonIgnore
    private List<PregnantWomenRequest> pregnantWomenRequests;

}
