package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.dto.PregnantWomenRequestDTO;
import com.edu.famBridge.repository.PregnantWomenRequestRepository;
import com.edu.famBridge.service.PregnantWomenRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "famBridge/pregnantWomenRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class PregnantWomenRequestController {
    @Autowired
    private PregnantWomenRequestService pregnantWomenRequestService;
    @Autowired
    private PregnantWomenRequestRepository pregnanentRegistrationRequestRepository;

    //Get request by request id  -----used------
    @GetMapping("/getPregnantWomenRequestByUniqueKey/{requestId}")
    public ResponseEntity<PregnantWomenRequestDTO> getPregnantWomenRequestByUniqueKey (@PathVariable long requestId){
        return ResponseEntity.ok(pregnantWomenRequestService.getPregnantWomenRequestByUniqueKey(requestId));
    }



        @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<PregnantWomenRequestDTO> savePregnantWomenRequest(
                @RequestParam("email") String email,
                @RequestParam("appointmentDate") LocalDate appointmentDate,
                @RequestPart("pregnancyProof") MultipartFile file){
            try {
                PregnantWomenRequestDTO dto = new PregnantWomenRequestDTO();


                if (file != null && !file.isEmpty()) {
                    dto.setEmail(email);
                    dto.setAppointmentDate(appointmentDate);
                    dto.setPregnancyProof(file.getBytes());
                }

                return ResponseEntity.ok(pregnantWomenRequestService.savePregnantWomenRequest(dto));
            } catch (IOException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/requestDetails/{phmArea}")
        public ResponseEntity<List<String>> getPregnantWomenRequestDetails(@PathVariable String phmArea){
            return ResponseEntity.ok(pregnantWomenRequestService.getPregnantWomenRequestDetails(phmArea));
        }

        @GetMapping("/proof/{requestId}")
        public ResponseEntity<byte[]> getPregnancyProof(@PathVariable Long requestId) {
            byte[] proof = pregnantWomenRequestService.getPregnancyProofByRequestId(requestId);

            if (proof == null) {
                return ResponseEntity.notFound().build();
            }

            String contentType = null;
            try {
                contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(proof));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (contentType == null) {
                // Default to jpeg if type cannot be guessed
                contentType = "image/jpeg";
            }

            // Extract file extension from content type
            String extension = contentType.split("/")[1];

            return ResponseEntity
                    .ok()
                    .header("Content-Type", contentType)
                    .header("Content-Disposition", "inline; filename=marriage_proof." + extension)
                    .body(proof);
        }



    }


