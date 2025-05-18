package com.edu.famBridge.entity;

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

public class MarriedCoupleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
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
    @Lob
    private byte[] marriageProof;

    @OneToOne(mappedBy = "marriedCoupleRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private MarriedCouple marriedCouple;


}
