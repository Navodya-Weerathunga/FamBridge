package com.edu.famBridge.serviceImpl;


import com.edu.famBridge.entity.AdviceEntity;
import com.edu.famBridge.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements com.edu.famBridge.service.AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public AdviceEntity addAdvice(AdviceEntity advice) {
        return adviceRepository.save(advice);
    }

    @Override
    public List<AdviceEntity> getAllAdvice() {
        return adviceRepository.findAll();
    }

    @Override
    public List<AdviceEntity> getAdviceByType(String type) {
        return adviceRepository.findByType(type);
    }

    @Override
    public AdviceEntity updateAdvice(Long id, AdviceEntity updatedAdvice) {
        AdviceEntity existingAdvice = adviceRepository.findById(id).orElse(null);
        if (existingAdvice != null) {
            existingAdvice.setAdvice(updatedAdvice.getAdvice());
            existingAdvice.setType(updatedAdvice.getType());
            return adviceRepository.save(existingAdvice);
        }
        return null;
    }

    @Override
    public void deleteAdvice(Long id) {
        adviceRepository.deleteById(id);
    }
}
