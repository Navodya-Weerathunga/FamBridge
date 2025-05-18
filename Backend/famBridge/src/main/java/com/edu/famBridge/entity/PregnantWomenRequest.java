package com.edu.famBridge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PregnantWomenRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marriedCoupleId", referencedColumnName = "marriedCoupleId", nullable = false)
    private MarriedCouple marriedCouple;

    private LocalDate requestDate;

    @Lob
    private byte[] pregnancyProof;

    private String status;

    private String firstName;
    private String email;
    private String assignMidwife;
    private String contactNo;
    private String nic;
    private String phmArea;
    private LocalDate appointmentDate;


    @OneToOne(mappedBy = "pregnantWomenRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private PregnantWomen pregnantWomen;

}
