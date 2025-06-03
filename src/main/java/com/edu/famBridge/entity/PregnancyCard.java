package com.edu.famBridge.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "pcard")

public class PregnancyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @ManyToOne
    @JoinColumn(name="pregnancy_record_no", referencedColumnName="pregnancyRecordNo")
    private PregnantWomen pregnantWomen;

    private String riskType;
    private String bloodGroup;
    private Double bmi;
    private Integer height;
    private Integer weight;
    private String allergies;
    private String fieldClinicName;
    private String hospitalClinicName;
    private String consultantObstetricians;
    private String antenatalRiskConditions;
    private Boolean consanguinity;
    private Boolean rubellaImmunization;
    private Boolean prePregnancyScreening;
    private Boolean preconceptionalFoicAcid;
    private Boolean historyOfSubfertility;
    private Boolean plannedPregnancy;
    private String lastUsedFamilyPlanningMethods;
    private LocalDate visitingDate;
    private String poa;

    @Enumerated(EnumType.STRING)
    private Gravidity gravidity;

    private Integer ageOfYoungestChild;
    private LocalDate LRMP;
    private LocalDate EDD;
    private LocalDate dateOf40Weeks;
    private LocalDate usCorrectedEdd;
    private String poaAtScan;
    private String poaAtRegistration;
    private LocalDate gestationPeriod;
    private Boolean contraceptiveMethod;
    private String contraceptiveDetails;
    private Integer wifeAge;
    private String wifeHighestEducation;
    private String wifeOccupation;
    private Integer husbandAge;
    private String husbandHighestEducation;
    private String husbandOccupation;
    private Boolean diabetesMellitus;
    private Boolean hypertension;
    private Boolean haematologicalDiseases;
    private Boolean twinOrMultiplePregnancies;
    private String others;
    private Boolean diabetes;
    private Boolean hypertensionMedHis;
    private Boolean cardiacDiseases;
    private Boolean renalDiseases;
    private Boolean hepaticDiseases;
    private Boolean psychiatricIllnesses;
    private Boolean epilepsy;
    private Boolean malignancies;
    private Boolean haematologicalDiseasesMedHis;
    private Boolean tuberculosis;
    private Boolean thyroidDiseases;
    private Boolean bronchialAsthma;
    private String pregnancy;
    private String antenatalComplications;
    private String placeOfDelivery;
    private String modeOfDelivery;
    private String outcome;
    private Double brithWeight;
    private String postnatalComplications;
    private String sexOfChild;
    private Integer ageOfChild;
    private Double fundalHeight;
    private LocalDate sfhDate;
    private String intendedHospital;
    private String modeOfTransport;
    private Double averageCost;
    private Double distanceFromHome;
    private String timeTakenToReach;
    private String intendedHospitalEmergency;
    private String modeOfTransportEmergency;
    private Double averageCostEmergency;
    private Double distanceFromHomeEmergency;
    private String timeTakenToReachEmergency;
    private String[] bmiHistory;


    public enum Gravidity{
        G, P, C;
    }


}
