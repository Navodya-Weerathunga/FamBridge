package com.edu.famBridge.service;


import com.edu.famBridge.entity.AdviceEntity;

import java.util.List;

public interface AdviceService {
    AdviceEntity addAdvice(AdviceEntity advice);
    List<AdviceEntity> getAllAdvice();
    List<AdviceEntity> getAdviceByType(String type);
    AdviceEntity updateAdvice(Long id, AdviceEntity updatedAdvice);
    void deleteAdvice(Long id);
}
