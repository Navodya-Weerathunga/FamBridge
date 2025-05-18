package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.PregnancyCardDTO;
import com.edu.famBridge.entity.PregnancyCard;
import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.repository.PregnancyCardRepository;
import com.edu.famBridge.repository.PregnantWomenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PregnancyCardServiceTest {

    @Mock
    private PregnancyCardRepository repository;

    @Mock
    private PregnantWomenRepository pregnantWomenRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PregnancyCardService service;

    private PregnancyCardDTO dto;
    private PregnancyCard entity;
    private PregnantWomen woman;

    @BeforeEach
    void setUp() {
        dto = new PregnancyCardDTO();
        dto.setCid(1L);
        dto.setPregnancyRecordNo(101);

        woman = new PregnantWomen();
        woman.setPregnancyRecordNo(101);

        entity = new PregnancyCard();
        entity.setCid(1L);
        entity.setPregnantWomen(woman);
    }

    @Test
    void testAddPregnancyCard() {
        when(pregnantWomenRepository.findById(dto.getPregnancyRecordNo())).thenReturn(Optional.of(woman));
        when(modelMapper.map(dto, PregnancyCard.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, PregnancyCardDTO.class)).thenReturn(dto);

        PregnancyCardDTO result = service.addPregnancyCard(dto);

        assertNotNull(result);
        assertEquals(101, result.getPregnancyRecordNo());
    }

    @Test
    void testGetAllPregnancyCards() {
        List<PregnancyCard> entities = List.of(entity);
        when(repository.findAll()).thenReturn(entities);
        when(modelMapper.map(entity, PregnancyCardDTO.class)).thenReturn(dto);

        List<PregnancyCardDTO> result = service.getAllPregnancyCards();

        assertEquals(1, result.size());
    }

    @Test
    void testGetPregnancyCardsByPregnancyRecordNo() {
        when(repository.findAllByPregnantWomen_PregnancyRecordNo(101)).thenReturn(List.of(entity));
        when(modelMapper.map(entity, PregnancyCardDTO.class)).thenReturn(dto);

        List<PregnancyCardDTO> result = service.getPregnancyCardsByPregnancyRecordNo(101);

        assertEquals(1, result.size());
    }


    @Test
    void testUpdatePregnancyCardByPregnancyRecordNo_NotFound() {
        when(repository.findByPregnantWomen_PregnancyRecordNo(101)).thenReturn(Optional.empty());

        PregnancyCardDTO result = service.updatePregnancyCardByPregnancyRecordNo(101, dto);

        assertNull(result);
    }
}
