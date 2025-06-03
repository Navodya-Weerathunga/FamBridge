package com.edu.famBridge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CancellationMessagedto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("user_name")
    private String userName;
    private String email;
    private String type;
    private LocalDateTime Date;
    @JsonProperty("meeting_time")
    private LocalTime meetingTime;
    @JsonProperty("meeting_date")
    private LocalDate meetingDate;
    private String Message;
    @JsonProperty("nic")
    private String nic;
    private String method;
    @JsonProperty("meeting_id")
    private Long meetingId;
    @JsonProperty("midwife_name")
    private String midwifeName;
}
