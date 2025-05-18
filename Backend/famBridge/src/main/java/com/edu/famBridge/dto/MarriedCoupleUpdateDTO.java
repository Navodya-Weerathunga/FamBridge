package com.edu.famBridge.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MarriedCoupleUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String occupation;

    private String husbandFirstName;
    private String husbandLastName;
    private String husbandContactNo;
    private String husbandOccupation;

    private String message;
}
