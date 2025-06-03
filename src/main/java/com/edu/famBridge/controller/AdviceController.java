package com.edu.famBridge.controller;

import com.edu.famBridge.entity.AdviceEntity;
import com.edu.famBridge.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advice")
@CrossOrigin(origins = "http://localhost:3000")  // Frontend runs on port 3000
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @PostMapping
    public AdviceEntity addAdvice(@RequestBody AdviceEntity advice) {
        return adviceService.addAdvice(advice);
    }

    @GetMapping
    public List<AdviceEntity> getAllAdvice() {
        return adviceService.getAllAdvice();
    }

    @GetMapping("/type/{type}")
    public List<AdviceEntity> getAdviceByType(@PathVariable String type) {
        return adviceService.getAdviceByType(type);
    }

    @PutMapping("/{id}")
    public AdviceEntity updateAdvice(@PathVariable Long id, @RequestBody AdviceEntity updatedAdvice) {
        return adviceService.updateAdvice(id, updatedAdvice);
    }

    @DeleteMapping("/{id}")
    public void deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdvice(id);
    }
}
