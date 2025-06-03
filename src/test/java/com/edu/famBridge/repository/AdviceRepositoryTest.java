//package com.edu.famBridge.repository;
//
//
//import com.edu.famBridge.entity.AdviceEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class AdviceRepositoryTest {
//
//    @Autowired
//    private AdviceRepository adviceRepository;
//
//    @Test
//    void testFindByType() {
//        // Given
//        AdviceEntity advice1 = new AdviceEntity();
//        advice1.setType("Pregnancy");
//        advice1.setAdvice("Eat healthy");
//
//        AdviceEntity advice2 = new AdviceEntity();
//        advice2.setType("Pre-Pregnancy");
//        advice2.setAdvice("Exercise daily");
//
//        adviceRepository.save(advice1);
//        adviceRepository.save(advice2);
//
//        // When
//        List<AdviceEntity> result = adviceRepository.findByType("Pregnancy");
//
//        // Then
//        assertEquals(1, result.size());
//        assertEquals("Eat healthy", result.get(0).getAdvice());
//    }
//}
