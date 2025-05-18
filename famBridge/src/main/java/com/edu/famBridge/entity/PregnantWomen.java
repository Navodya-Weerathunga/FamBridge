package com.edu.famBridge.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="pregnantWomen")

public class PregnantWomen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pregnancyRecordNo;

    private String motherName;
    private Integer motherAge;

    @OneToOne(mappedBy = "pregnantWomen")
    private PregnancyCard pregnancyCard;


    public Integer getPregnancyRecordNo() {
        return pregnancyRecordNo;
    }

    public void setPregnancyRecordNo(Integer pregnancyRecordNo) {
        this.pregnancyRecordNo = pregnancyRecordNo;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Integer getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(Integer motherAge) {
        this.motherAge = motherAge;
    }


}
