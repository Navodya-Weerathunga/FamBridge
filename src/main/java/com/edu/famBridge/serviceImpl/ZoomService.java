package com.edu.famBridge.serviceImpl;


import com.edu.famBridge.repository.VisitChannelingRepository;
import com.edu.famBridge.service.MidwifeManualMeetingCreateService;
import com.edu.famBridge.service.VisitChannelingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;


@Service

public class ZoomService {
    @Autowired
    private VisitChannelingService visitChannelingService;
    @Autowired
    private MidwifeManualMeetingCreateService midwifeManualMeetingCreateService;
    private static final Logger logger = LoggerFactory.getLogger(ZoomService.class);
    private final RestTemplate restTemplate;
    private final VisitChannelingRepository visitRepo;

    // Zoom API Credentials
    private final String clientId = "8CX9BBS1S_6kFwvUMF_4KA";
    private final String clientSecret = "JdM1GU8uMg53NFR3zo7rU0gi11LMkuFN";
    private final String accountId = "GAHLweWxTau1Wn79XXX9GQ";

    // Constructor-based Dependency Injection
    public ZoomService(RestTemplate restTemplate, VisitChannelingRepository visitRepo) {
        this.restTemplate = restTemplate;
        this.visitRepo = visitRepo;
    }


    public String getZoomAccessToken() {
        String url = "https://zoom.us/oauth/token?grant_type=account_credentials&account_id=" + accountId;

        HttpHeaders headers = new HttpHeaders();
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            logger.info("Zoom Access Token Retrieved Successfully");
        } catch (Exception e) {
            logger.error("Failed to get Zoom access token: {}", e.getMessage());
            throw new RuntimeException("Failed to get Zoom access token: " + e.getMessage());
        }

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().get("access_token").toString();
        } else {
            throw new RuntimeException("Error getting Zoom token: " + response.getStatusCode());
        }
    }

    //Save notice
    public String createMeeting(String startTime, Long chaId) {
        String url = "https://api.zoom.us/v2/users/me/meetings";

        // Request Body
        Map<String, Object> requestBody = Map.of(
                "topic", "Scheduled Meeting",
                "type", 2,
                "start_time", startTime,
                "duration", 30,
                "timezone", "UTC"
        );

        // Request Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String token = getZoomAccessToken();
        System.out.println("Using Zoom API Token: " + token);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            logger.info("Zoom API Response: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String meetingLink = response.getBody().get("join_url").toString(); // Extract Meeting Link

                // **Update the virtual_channeling entity with the meeting link**
                visitChannelingService.updateVaccineChannelingForMeeting(chaId, meetingLink);

                return meetingLink; // Return the meeting link
            } else {
                throw new RuntimeException("Error creating Zoom meeting: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            logger.error("Zoom API Error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Failed to create Zoom meeting: " + e.getResponseBodyAsString(), e);
        }
    }

//Delete notice
    public String createMeetings(String startTime, Long chaId) {
        String url = "https://api.zoom.us/v2/users/me/meetings";

        // Request Body
        Map<String, Object> requestBody = Map.of(
                "topic", "Scheduled Meeting",
                "type", 2,
                "start_time", startTime,
                "duration", 30,
                "timezone", "UTC"
        );

        // Request Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String token = getZoomAccessToken();
        System.out.println("Using Zoom API Token: " + token);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            logger.info("Zoom API Response: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String meetingLink = response.getBody().get("join_url").toString(); // Extract Meeting Link

                // **Update the virtual_channeling entity with the meeting link**
                midwifeManualMeetingCreateService.updateChannelingForMeeting(chaId, meetingLink);

                return meetingLink; // Return the meeting link
            } else {
                throw new RuntimeException("Error creating Zoom meeting: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            logger.error("Zoom API Error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Failed to create Zoom meeting: " + e.getResponseBodyAsString(), e);
        }
    }



}
