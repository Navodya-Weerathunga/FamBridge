package com.edu.famBridge.serviceIMPL;

import com.edu.famBridge.dto.MidwifeDTO;
import com.edu.famBridge.entity.Midwife;
import com.edu.famBridge.repository.MidwifeRepository;
import com.edu.famBridge.serviceImpl.MidwifeServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MidwifeServiceImplTest {

    @InjectMocks
    private MidwifeServiceImpl midwifeService;

    @Mock
    private MidwifeRepository midwifeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @Mock
    private MimeMessageHelper mimeMessageHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMidwife_andSendEmail() throws Exception {
        // Arrange
        MidwifeDTO dto = new MidwifeDTO();
        dto.setNic("NIC123");
        dto.setEmail("midwife@example.com");
        dto.setPassword("plainPassword");

        Midwife midwife = new Midwife();
        when(midwifeRepository.findByNic("NIC123")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(modelMapper.map(dto, Midwife.class)).thenReturn(midwife);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        MidwifeDTO result = midwifeService.saveMidwife(dto);

        // Assert
        assertEquals(200, result.getStatusCode());
        assertEquals("Midwife save successfully.", result.getMessage());
        verify(midwifeRepository, times(1)).save(midwife);
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    void saveMidwife_alreadyExists() {
        // Arrange
        MidwifeDTO dto = new MidwifeDTO();
        dto.setNic("NIC123");
        Midwife existing = new Midwife();
        when(midwifeRepository.findByNic("NIC123")).thenReturn(Optional.of(existing));

        // Act
        MidwifeDTO result = midwifeService.saveMidwife(dto);

        // Assert
        assertEquals("Midwife Already Exists!", result.getMessage());
        verify(midwifeRepository, never()).save(any());
    }


    @Test
    void sendLoginDetails_sendEmailSuccessfully() throws Exception {
        // Arrange
        String email = "test@example.com";
        String password = "securePass123";

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        midwifeService.sendLoginDetails(email, password);

        // Assert
        verify(javaMailSender).send(mimeMessage);
    }


    @Test
    void sendLoginDetails_throwRuntimeException_onMessagingException() {
        // Arrange
        String email = "fail@example.com";
        String password = "12345";

        when(javaMailSender.createMimeMessage()).thenThrow(new RuntimeException("Unable to send login details"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                midwifeService.sendLoginDetails(email, password)
        );

        assertEquals("Unable to send login details", ex.getMessage());
    }

    @Test
    void loginMidwife_invalidEmail() {
        // Arrange
        MidwifeDTO inputDTO = new MidwifeDTO();
        inputDTO.setEmail("notfound@example.com");
        inputDTO.setPassword("password");

        when(midwifeRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        // Act
        MidwifeDTO response = midwifeService.loginMidwife(inputDTO);

        // Assert
        assertEquals(404, response.getStatusCode());
        assertEquals("Email not found", response.getMessage());
        assertFalse(response.isLogin());
    }

    @Test
    void loginMidwife_incorrectPassword() {
        // Arrange
        MidwifeDTO inputDTO = new MidwifeDTO();
        inputDTO.setEmail("test@example.com");
        inputDTO.setPassword("wrongPassword");

        Midwife midwife = new Midwife();
        midwife.setEmail("test@example.com");
        midwife.setPassword("encodedPassword");

        when(midwifeRepository.findByEmail("test@example.com")).thenReturn(Optional.of(midwife));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // Act
        MidwifeDTO response = midwifeService.loginMidwife(inputDTO);

        // Assert
        assertEquals(401, response.getStatusCode());
        assertEquals("Invalid password", response.getMessage());
        assertFalse(response.isLogin());
    }

    @Test
    void loginMidwife_validCredentials() {
        // Arrange
        MidwifeDTO inputDTO = new MidwifeDTO();
        inputDTO.setEmail("valid@example.com");
        inputDTO.setPassword("correctPassword");

        Midwife midwife = new Midwife();
        midwife.setEmail("valid@example.com");
        midwife.setPassword("encodedPassword");
        midwife.setMidwifeType("Field");
        midwife.setWorkingArea(List.of("Area A"));
        midwife.setMohArea("MOH A");

        when(midwifeRepository.findByEmail("valid@example.com")).thenReturn(Optional.of(midwife));
        when(passwordEncoder.matches("correctPassword", "encodedPassword")).thenReturn(true);

        // Act
        MidwifeDTO response = midwifeService.loginMidwife(inputDTO);

        // Assert
        assertEquals(200, response.getStatusCode());
        assertEquals("Login Success", response.getMessage());
        assertTrue(response.isLogin());
        assertEquals("valid@example.com", response.getEmail());
        assertEquals("Field", response.getMidwifeType());
        assertEquals(List.of("Area A"), response.getWorkingArea());
        assertEquals("MOH A", response.getMohArea());
    }


    @Test
    void getAllMidwives() {
        // Arrange
        Midwife midwife1 = new Midwife();
        midwife1.setEmail("midwife1@example.com");
        Midwife midwife2 = new Midwife();
        midwife2.setEmail("midwife2@example.com");
        List<Midwife> midwives = Arrays.asList(midwife1, midwife2);

        when(midwifeRepository.findAll()).thenReturn(midwives);

        // Act
        MidwifeDTO result = midwifeService.getAllMidwives();

        // Assert
        assertEquals(200, result.getStatusCode());
        assertEquals("Successful", result.getMessage());
        assertNotNull(result.getMidwifeList());
        assertEquals(2, result.getMidwifeList().size());
        assertEquals("midwife1@example.com", result.getMidwifeList().get(0).getEmail());
        assertEquals("midwife2@example.com", result.getMidwifeList().get(1).getEmail());
    }

    @Test
    void getAllMidwives_returns404_whenNoMidwivesFound() {
        // Arrange
        when(midwifeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        MidwifeDTO result = midwifeService.getAllMidwives();

        // Assert
        assertEquals(404, result.getStatusCode());
        assertEquals("No midwife found", result.getMessage());
        assertNull(result.getMidwifeList());
    }

    @Test
    void getMidwifeByEmail() {
    }

    @Test
    void updatePassword_successfulUpdate() {
        // Arrange
        MidwifeDTO dto = new MidwifeDTO();
        dto.setEmail("midwife@example.com");
        dto.setPassword("newPass123");
        dto.setConfirmPassword("newPass123");

        Midwife midwife = new Midwife();
        midwife.setEmail("midwife@example.com");

        when(midwifeRepository.findByEmail("midwife@example.com")).thenReturn(Optional.of(midwife));
        when(passwordEncoder.encode("newPass123")).thenReturn("encodedPassword");

        // Act
        MidwifeDTO result = midwifeService.updatePassword(dto);

        // Assert
        assertEquals(200, result.getStatusCode());
        assertEquals("Password Updated Successfully", result.getMessage());
        verify(midwifeRepository).save(midwife);
    }

    @Test
    void updatePassword_passwordMismatch() {
        // Arrange
        MidwifeDTO dto = new MidwifeDTO();
        dto.setEmail("midwife@example.com");
        dto.setPassword("newPass123");
        dto.setConfirmPassword("wrongConfirm");

        Midwife midwife = new Midwife();
        midwife.setEmail("midwife@example.com");

        when(midwifeRepository.findByEmail("midwife@example.com")).thenReturn(Optional.of(midwife));

        // Act
        MidwifeDTO result = midwifeService.updatePassword(dto);

        // Assert
        assertEquals(400, result.getStatusCode());
        assertEquals("Password Not Match with the Confirm Password", result.getMessage());
        verify(midwifeRepository, never()).save(any());
    }

    @Test
    void updatePassword_midwifeNotFound() {
        // Arrange
        MidwifeDTO dto = new MidwifeDTO();
        dto.setEmail("unknown@example.com");
        dto.setPassword("any");
        dto.setConfirmPassword("any");

        when(midwifeRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        // Act
        MidwifeDTO result = midwifeService.updatePassword(dto);

        // Assert
        assertEquals("Midwife Not Found", result.getMessage());
        assertEquals(0, result.getStatusCode()); // Assuming default value
        verify(midwifeRepository, never()).save(any());
    }
    @Test
    void findMidwifeByWorkingArea() {
    }

    @Test
    void updateProfile() {
    }

}