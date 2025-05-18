package com.edu.famBridge.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public PregnantWomen getPregnantWomen() {
        return pregnantWomen;
    }

    public void setPregnantWomen(PregnantWomen pregnantWomen) {
        this.pregnantWomen = pregnantWomen;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getFieldClinicName() {
        return fieldClinicName;
    }

    public void setFieldClinicName(String fieldClinicName) {
        this.fieldClinicName = fieldClinicName;
    }

    public String getHospitalClinicName() {
        return hospitalClinicName;
    }

    public void setHospitalClinicName(String hospitalClinicName) {
        this.hospitalClinicName = hospitalClinicName;
    }

    public String getConsultantObstetricians() {
        return consultantObstetricians;
    }

    public void setConsultantObstetricians(String consultantObstetricians) {
        this.consultantObstetricians = consultantObstetricians;
    }

    public String getAntenatalRiskConditions() {
        return antenatalRiskConditions;
    }

    public void setAntenatalRiskConditions(String antenatalRiskConditions) {
        this.antenatalRiskConditions = antenatalRiskConditions;
    }

    public Boolean getConsanguinity() {
        return consanguinity;
    }

    public void setConsanguinity(Boolean consanguinity) {
        this.consanguinity = consanguinity;
    }

    public Boolean getRubellaImmunization() {
        return rubellaImmunization;
    }

    public void setRubellaImmunization(Boolean rubellaImmunization) {
        this.rubellaImmunization = rubellaImmunization;
    }

    public Boolean getPrePregnancyScreening() {
        return prePregnancyScreening;
    }

    public void setPrePregnancyScreening(Boolean prePregnancyScreening) {
        this.prePregnancyScreening = prePregnancyScreening;
    }

    public Boolean getPreconceptionalFoicAcid() {
        return preconceptionalFoicAcid;
    }

    public void setPreconceptionalFoicAcid(Boolean preconceptionalFoicAcid) {
        this.preconceptionalFoicAcid = preconceptionalFoicAcid;
    }

    public Boolean getHistoryOfSubfertility() {
        return historyOfSubfertility;
    }

    public void setHistoryOfSubfertility(Boolean historyOfSubfertility) {
        this.historyOfSubfertility = historyOfSubfertility;
    }

    public Boolean getPlannedPregnancy() {
        return plannedPregnancy;
    }

    public void setPlannedPregnancy(Boolean plannedPregnancy) {
        this.plannedPregnancy = plannedPregnancy;
    }

    public String getLastUsedFamilyPlanningMethods() {
        return lastUsedFamilyPlanningMethods;
    }

    public void setLastUsedFamilyPlanningMethods(String lastUsedFamilyPlanningMethods) {
        this.lastUsedFamilyPlanningMethods = lastUsedFamilyPlanningMethods;
    }

    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(LocalDate visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getPoa() {
        return poa;
    }

    public void setPoa(String poa) {
        this.poa = poa;
    }

    public Gravidity getGravidity() {
        return gravidity;
    }

    public void setGravidity(Gravidity gravidity) {
        this.gravidity = gravidity;
    }

    public Integer getAgeOfYoungestChild() {
        return ageOfYoungestChild;
    }

    public void setAgeOfYoungestChild(Integer ageOfYoungestChild) {
        this.ageOfYoungestChild = ageOfYoungestChild;
    }

    public LocalDate getLRMP() {
        return LRMP;
    }

    public void setLRMP(LocalDate LRMP) {
        this.LRMP = LRMP;
    }

    public LocalDate getEDD() {
        return EDD;
    }

    public void setEDD(LocalDate EDD) {
        this.EDD = EDD;
    }

    public LocalDate getDateOf40Weeks() {
        return dateOf40Weeks;
    }

    public void setDateOf40Weeks(LocalDate dateOf40Weeks) {
        this.dateOf40Weeks = dateOf40Weeks;
    }

    public LocalDate getUsCorrectedEdd() {
        return usCorrectedEdd;
    }

    public void setUsCorrectedEdd(LocalDate usCorrectedEdd) {
        this.usCorrectedEdd = usCorrectedEdd;
    }

    public String getPoaAtScan() {
        return poaAtScan;
    }

    public void setPoaAtScan(String poaAtScan) {
        this.poaAtScan = poaAtScan;
    }

    public String getPoaAtRegistration() {
        return poaAtRegistration;
    }

    public void setPoaAtRegistration(String poaAtRegistration) {
        this.poaAtRegistration = poaAtRegistration;
    }

    public LocalDate getGestationPeriod() {
        return gestationPeriod;
    }

    public void setGestationPeriod(LocalDate gestationPeriod) {
        this.gestationPeriod = gestationPeriod;
    }

    public Boolean getContraceptiveMethod() {
        return contraceptiveMethod;
    }

    public void setContraceptiveMethod(Boolean contraceptiveMethod) {
        this.contraceptiveMethod = contraceptiveMethod;
    }

    public String getContraceptiveDetails() {
        return contraceptiveDetails;
    }

    public void setContraceptiveDetails(String contraceptiveDetails) {
        this.contraceptiveDetails = contraceptiveDetails;
    }

    public Integer getWifeAge() {
        return wifeAge;
    }

    public void setWifeAge(Integer wifeAge) {
        this.wifeAge = wifeAge;
    }

    public String getWifeHighestEducation() {
        return wifeHighestEducation;
    }

    public void setWifeHighestEducation(String wifeHighestEducation) {
        this.wifeHighestEducation = wifeHighestEducation;
    }

    public String getWifeOccupation() {
        return wifeOccupation;
    }

    public void setWifeOccupation(String wifeOccupation) {
        this.wifeOccupation = wifeOccupation;
    }

    public Integer getHusbandAge() {
        return husbandAge;
    }

    public void setHusbandAge(Integer husbandAge) {
        this.husbandAge = husbandAge;
    }

    public String getHusbandHighestEducation() {
        return husbandHighestEducation;
    }

    public void setHusbandHighestEducation(String husbandHighestEducation) {
        this.husbandHighestEducation = husbandHighestEducation;
    }

    public String getHusbandOccupation() {
        return husbandOccupation;
    }

    public void setHusbandOccupation(String husbandOccupation) {
        this.husbandOccupation = husbandOccupation;
    }

    public Boolean getDiabetesMellitus() {
        return diabetesMellitus;
    }

    public void setDiabetesMellitus(Boolean diabetesMellitus) {
        this.diabetesMellitus = diabetesMellitus;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getHaematologicalDiseases() {
        return haematologicalDiseases;
    }

    public void setHaematologicalDiseases(Boolean haematologicalDiseases) {
        this.haematologicalDiseases = haematologicalDiseases;
    }

    public Boolean getTwinOrMultiplePregnancies() {
        return twinOrMultiplePregnancies;
    }

    public void setTwinOrMultiplePregnancies(Boolean twinOrMultiplePregnancies) {
        this.twinOrMultiplePregnancies = twinOrMultiplePregnancies;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean getHypertensionMedHis() {
        return hypertensionMedHis;
    }

    public void setHypertensionMedHis(Boolean hypertensionMedHis) {
        this.hypertensionMedHis = hypertensionMedHis;
    }

    public Boolean getCardiacDiseases() {
        return cardiacDiseases;
    }

    public void setCardiacDiseases(Boolean cardiacDiseases) {
        this.cardiacDiseases = cardiacDiseases;
    }

    public Boolean getRenalDiseases() {
        return renalDiseases;
    }

    public void setRenalDiseases(Boolean renalDiseases) {
        this.renalDiseases = renalDiseases;
    }

    public Boolean getHepaticDiseases() {
        return hepaticDiseases;
    }

    public void setHepaticDiseases(Boolean hepaticDiseases) {
        this.hepaticDiseases = hepaticDiseases;
    }

    public Boolean getPsychiatricIllnesses() {
        return psychiatricIllnesses;
    }

    public void setPsychiatricIllnesses(Boolean psychiatricIllnesses) {
        this.psychiatricIllnesses = psychiatricIllnesses;
    }

    public Boolean getEpilepsy() {
        return epilepsy;
    }

    public void setEpilepsy(Boolean epilepsy) {
        this.epilepsy = epilepsy;
    }

    public Boolean getMalignancies() {
        return malignancies;
    }

    public void setMalignancies(Boolean malignancies) {
        this.malignancies = malignancies;
    }

    public Boolean getHaematologicalDiseasesMedHis() {
        return haematologicalDiseasesMedHis;
    }

    public void setHaematologicalDiseasesMedHis(Boolean haematologicalDiseasesMedHis) {
        this.haematologicalDiseasesMedHis = haematologicalDiseasesMedHis;
    }

    public Boolean getTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(Boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public Boolean getThyroidDiseases() {
        return thyroidDiseases;
    }

    public void setThyroidDiseases(Boolean thyroidDiseases) {
        this.thyroidDiseases = thyroidDiseases;
    }

    public Boolean getBronchialAsthma() {
        return bronchialAsthma;
    }

    public void setBronchialAsthma(Boolean bronchialAsthma) {
        this.bronchialAsthma = bronchialAsthma;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getAntenatalComplications() {
        return antenatalComplications;
    }

    public void setAntenatalComplications(String antenatalComplications) {
        this.antenatalComplications = antenatalComplications;
    }

    public String getPlaceOfDelivery() {
        return placeOfDelivery;
    }

    public void setPlaceOfDelivery(String placeOfDelivery) {
        this.placeOfDelivery = placeOfDelivery;
    }

    public String getModeOfDelivery() {
        return modeOfDelivery;
    }

    public void setModeOfDelivery(String modeOfDelivery) {
        this.modeOfDelivery = modeOfDelivery;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Double getBrithWeight() {
        return brithWeight;
    }

    public void setBrithWeight(Double brithWeight) {
        this.brithWeight = brithWeight;
    }

    public String getPostnatalComplications() {
        return postnatalComplications;
    }

    public void setPostnatalComplications(String postnatalComplications) {
        this.postnatalComplications = postnatalComplications;
    }

    public String getSexOfChild() {
        return sexOfChild;
    }

    public void setSexOfChild(String sexOfChild) {
        this.sexOfChild = sexOfChild;
    }

    public Integer getAgeOfChild() {
        return ageOfChild;
    }

    public void setAgeOfChild(Integer ageOfChild) {
        this.ageOfChild = ageOfChild;
    }

    public Double getFundalHeight() {
        return fundalHeight;
    }

    public void setFundalHeight(Double fundalHeight) {
        this.fundalHeight = fundalHeight;
    }

    public LocalDate getSfhDate() {
        return sfhDate;
    }

    public void setSfhDate(LocalDate sfhDate) {
        this.sfhDate = sfhDate;
    }

    public String getIntendedHospital() {
        return intendedHospital;
    }

    public void setIntendedHospital(String intendedHospital) {
        this.intendedHospital = intendedHospital;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public Double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Double averageCost) {
        this.averageCost = averageCost;
    }

    public Double getDistanceFromHome() {
        return distanceFromHome;
    }

    public void setDistanceFromHome(Double distanceFromHome) {
        this.distanceFromHome = distanceFromHome;
    }

    public String getTimeTakenToReach() {
        return timeTakenToReach;
    }

    public void setTimeTakenToReach(String timeTakenToReach) {
        this.timeTakenToReach = timeTakenToReach;
    }

    public String getIntendedHospitalEmergency() {
        return intendedHospitalEmergency;
    }

    public void setIntendedHospitalEmergency(String intendedHospitalEmergency) {
        this.intendedHospitalEmergency = intendedHospitalEmergency;
    }

    public String getModeOfTransportEmergency() {
        return modeOfTransportEmergency;
    }

    public void setModeOfTransportEmergency(String modeOfTransportEmergency) {
        this.modeOfTransportEmergency = modeOfTransportEmergency;
    }

    public Double getAverageCostEmergency() {
        return averageCostEmergency;
    }

    public void setAverageCostEmergency(Double averageCostEmergency) {
        this.averageCostEmergency = averageCostEmergency;
    }

    public Double getDistanceFromHomeEmergency() {
        return distanceFromHomeEmergency;
    }

    public void setDistanceFromHomeEmergency(Double distanceFromHomeEmergency) {
        this.distanceFromHomeEmergency = distanceFromHomeEmergency;
    }

    public String getTimeTakenToReachEmergency() {
        return timeTakenToReachEmergency;
    }

    public void setTimeTakenToReachEmergency(String timeTakenToReachEmergency) {
        this.timeTakenToReachEmergency = timeTakenToReachEmergency;
    }

    public String[] getBmiHistory() {
        return bmiHistory;
    }

    public void setBmiHistory(String[] bmiHistory) {
        this.bmiHistory = bmiHistory;
    }
}
