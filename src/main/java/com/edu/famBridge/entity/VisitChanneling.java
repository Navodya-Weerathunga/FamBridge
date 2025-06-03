package com.edu.famBridge.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VisitChanneling {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long cha_id;

        @NotBlank(message = "NIC cannot be null or blank")
        @Column(nullable = false)
        @Size(min = 9, max = 12, message = "NIC must be 12 characters long")
        private String nic;

        @NotBlank(message = "user name cannot be null")
        @Column(nullable = false)
        private String channeling_user_name;

        private Integer channeling_number;

        @NotBlank(message = " telephone number cannot be null")
        @Column(nullable = false)
        private String channeling_telephone;

        @NotBlank(message = "midwife name cannot be null")
        @Column(nullable = false)
        private String channeling_midwife;

        private LocalDate channeling_date;

        @NotBlank(message = "midwife time cannot be null")
        @Column(nullable = false)
        private String channeling_time;

        @NotNull(message = "date cannot be null")
        @Column(nullable = false)
        private LocalDate channeling_midwife_date;

        private String meetingLink;

        private int channel_number;

        private int status;

        private Long midwife_id;

        private LocalTime arriving_time;

        private int admin_status;

        private String email;
    }

