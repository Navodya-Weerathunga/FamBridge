package com.edu.famBridge.service;

import com.edu.famBridge.entity.AdviceEntity;
import com.edu.famBridge.repository.AdviceRepository;
import com.edu.famBridge.serviceImpl.AdviceServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdviceServiceImplTest {

    @Mock
    private AdviceRepository adviceRepository;

    @InjectMocks
    private AdviceServiceImpl adviceService;

    @Test
    void testAddAdvice() {
        AdviceEntity advice = new AdviceEntity("Pregnancy", "Stay hydrated.");
        when(adviceRepository.save(advice)).thenReturn(advice);

        AdviceEntity result = adviceService.addAdvice(advice);

        assertEquals("Pregnancy", result.getType());
        verify(adviceRepository, times(1)).save(advice);
    }

    @Test
    void testGetAllAdvice() {
        List<AdviceEntity> list = Arrays.asList(new AdviceEntity("Pregnancy", "Test1"));
        when(adviceRepository.findAll()).thenReturn(list);

        List<AdviceEntity> result = adviceService.getAllAdvice();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateAdvice() {
        AdviceEntity existing = new AdviceEntity("Pregnancy", "Old");
        existing.setId(1L);
        AdviceEntity updated = new AdviceEntity("Pre-Pregnancy", "New");

        when(adviceRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(adviceRepository.save(existing)).thenReturn(existing);

        AdviceEntity result = adviceService.updateAdvice(1L, updated);

        assertEquals("New", result.getAdvice());
        assertEquals("Pre-Pregnancy", result.getType());
    }

    @Test
    void testDeleteAdvice() {
        adviceService.deleteAdvice(1L);
        verify(adviceRepository, times(1)).deleteById(1L);
    }
}
