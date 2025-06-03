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

public class PregnantWomen {

    @Id
    private int pregnancyRecordNo;

    @OneToOne(optional = true)
    @JoinColumn(name = "requestId", nullable = false)
    private PregnantWomenRequest pregnantWomenRequest;

    private LocalDate registeredDate;

    private String firstName;
    private String email;
    private String assignMidwife;
    private String contactNo;
    private String nic;
    private String phmArea;

    @OneToMany (mappedBy = "pregnantWomen", cascade = CascadeType.ALL)
    private List<PregnancyCard> pregnancyCards = new ArrayList<>();

}
