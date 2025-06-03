package com.edu.famBridge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CancelationMessage {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

        @Column(name = "user_name")
        private String userName;
        private String email;
        private String type;
        private LocalDateTime Date;
        @Column(name = "meeting_time")
        private LocalTime meetingTime;
        @Column(name = "meeting_date")
        private LocalDate meetingDate;
        private String Message;
        @Column(name = "nic")
        private String nic;
        private String method;
        @Column(name = "meeting_id")
        private Long meetingId;
        @Column(name="midwife_name")
        private String midwifeName;

}
