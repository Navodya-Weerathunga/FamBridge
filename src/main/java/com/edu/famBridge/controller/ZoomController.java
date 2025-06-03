package com.edu.famBridge.controller;

import com.edu.famBridge.serviceImpl.ZoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/zoom")
@CrossOrigin(origins = "http://localhost:3000")
public class ZoomController {


    private final ZoomService zoomService;

    public ZoomController(ZoomService zoomService) {
        this.zoomService = zoomService;
    }

    @GetMapping("/token")
    public String getToken() {
        return zoomService.getZoomAccessToken();
    }

    //save
    @PostMapping("/create-meeting")
    public ResponseEntity<Map<String, String>> createMeeting(@RequestBody Map<String, Object> payload) {
        String startTime = (String) payload.get("start_time");
        Long chaId = Long.valueOf(payload.get("cha_id").toString());

        try {
            String meetingLink = zoomService.createMeeting(startTime, chaId);
            Map<String, String> response = new HashMap<>();
            response.put("meetingLink", meetingLink);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create Zoom meeting"));
        }
    }

    //Delete
    @PostMapping("/create-meetings")
    public ResponseEntity<Map<String, String>> createMeetings(@RequestBody Map<String, Object> payload) {
        try {
            String startTime = (String) payload.get("start_time");

            // Validate "id" before using it
            if (payload.get("id") == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Missing 'id' field in request"));
            }

            Long chaId = Long.valueOf(payload.get("id").toString());

            String meetingLink = zoomService.createMeetings(startTime, chaId);
            Map<String, String> response = new HashMap<>();
            response.put("meetingLink", meetingLink);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();  // Print the stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create Zoom meeting"));
        }
    }


}

