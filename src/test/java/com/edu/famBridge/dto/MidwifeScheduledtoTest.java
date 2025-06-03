package com.edu.famBridge.dto;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MidwifeScheduledtoTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        LocalDate date = LocalDate.of(2025, 4, 11);
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);

        MidwifeScheduledto dto = new MidwifeScheduledto(
                1L,
                "MW123",
                "Jane Doe",
                start,
                end,
                date,
                5,
                "Clinic",
                "Downtown"
        );

        assertEquals(1L, dto.getId());
        assertEquals("MW123", dto.getMidwifeId());
        assertEquals("Jane Doe", dto.getMidwifeName());
        assertEquals(start, dto.getStartTime());
        assertEquals(end, dto.getEndTime());
        assertEquals(date, dto.getAvailableDate());
        assertEquals(5, dto.getAppointmentsPerDay());
        assertEquals("Clinic", dto.getWorkType());
        assertEquals("Downtown", dto.getArea());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        MidwifeScheduledto dto = new MidwifeScheduledto();

        dto.setId(2L);
        dto.setMidwifeId("MW456");
        dto.setMidwifeName("Anna Smith");
        dto.setStartTime(LocalTime.of(10, 0));
        dto.setEndTime(LocalTime.of(18, 0));
        dto.setAvailableDate(LocalDate.of(2025, 4, 12));
        dto.setAppointmentsPerDay(4);
        dto.setWorkType("Home Visit");
        dto.setArea("Uptown");

        assertEquals(2L, dto.getId());
        assertEquals("MW456", dto.getMidwifeId());
        assertEquals("Anna Smith", dto.getMidwifeName());
        assertEquals(LocalTime.of(10, 0), dto.getStartTime());
        assertEquals(LocalTime.of(18, 0), dto.getEndTime());
        assertEquals(LocalDate.of(2025, 4, 12), dto.getAvailableDate());
        assertEquals(4, dto.getAppointmentsPerDay());
        assertEquals("Home Visit", dto.getWorkType());
        assertEquals("Uptown", dto.getArea());
    }

    @Test
    void testEqualsAndHashCode() {
        MidwifeScheduledto dto1 = new MidwifeScheduledto(
                1L, "MW123", "Jane Doe",
                LocalTime.of(9, 0), LocalTime.of(17, 0),
                LocalDate.of(2025, 4, 11),
                5, "Clinic", "Downtown"
        );

        MidwifeScheduledto dto2 = new MidwifeScheduledto(
                1L, "MW123", "Jane Doe",
                LocalTime.of(9, 0), LocalTime.of(17, 0),
                LocalDate.of(2025, 4, 11),
                5, "Clinic", "Downtown"
        );

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
