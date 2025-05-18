package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.service.MarriedCoupleRequestService;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("famBridge/marriedCoupleRequest")

public class MarriedCoupleRequestController {
    @Autowired
    private MarriedCoupleRequestService marriedCoupleRequestService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MarriedCoupleRequestDTO> saveMarriedCoupleRequest(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("nic") String nic,
            @RequestParam("address") String address,
            @RequestParam("province") String province,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("mohArea") String mohArea,
            @RequestParam("gramaNiladhariDivision") String gramaNiladhariDivision,
            @RequestParam("email") String email,
            @RequestParam("appointmentDate") LocalDate appointmentDate,
            @RequestParam("contactNumber") String contactNumber,
            @RequestPart(value = "marriageProof", required = false) MultipartFile file) {
        try {
            MarriedCoupleRequestDTO marriedCoupleRequestDTO = new MarriedCoupleRequestDTO();
            marriedCoupleRequestDTO.setFirstName(firstName);
            marriedCoupleRequestDTO.setLastName(lastName);
            marriedCoupleRequestDTO.setNic(nic);
            marriedCoupleRequestDTO.setAddress(address);
            marriedCoupleRequestDTO.setProvince(province);
            marriedCoupleRequestDTO.setDistrict(district);
            marriedCoupleRequestDTO.setCity(city);
            marriedCoupleRequestDTO.setMohArea(mohArea);
            marriedCoupleRequestDTO.setGramaNiladhariDivision(gramaNiladhariDivision);
            marriedCoupleRequestDTO.setEmail(email);
            marriedCoupleRequestDTO.setAppointmentDate(appointmentDate);
            marriedCoupleRequestDTO.setContactNo(contactNumber);

            if (file != null && !file.isEmpty()) {
                marriedCoupleRequestDTO.setMarriageProof(file.getBytes());
            }

            return ResponseEntity.ok(marriedCoupleRequestService.saveMarriedCoupleRequest(marriedCoupleRequestDTO));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/requestDetails/{mohArea}")
    public ResponseEntity<List<String>> getMarriedCoupleRequestDetails(@PathVariable String mohArea){
        return ResponseEntity.ok(marriedCoupleRequestService.getMarriedCoupleRequestDetails(mohArea));
    }

    @GetMapping("/requestDetailsById/{requestId}")
    public ResponseEntity<List<String>> getMarriedCoupleRequestDetailsById(@PathVariable Long requestId){
        return ResponseEntity.ok(marriedCoupleRequestService.getMarriedCoupleRequestDetailsById(requestId));
    }

    @GetMapping("/proof/{requestId}")
    public ResponseEntity<byte[]> getMarriageProof(@PathVariable Long requestId) {
        byte[] proof = marriedCoupleRequestService.getMarriageProofByRequestId(requestId);

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
