package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.PregnancyCardDTO;
import com.edu.famBridge.entity.PregnancyCard;

import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.repository.PregnancyCardRepository;

import com.edu.famBridge.repository.PregnantWomenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PregnancyCardService {

    @Autowired
    private PregnancyCardRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PregnantWomenRepository pregnantWomenRepository;

    private PregnancyCard convertToEntity(PregnancyCardDTO dto){
        return modelMapper.map(dto, PregnancyCard.class);
    }


    private PregnancyCardDTO convertToDTO(PregnancyCard card){
        PregnancyCardDTO dto = modelMapper.map(card, PregnancyCardDTO.class);
        if (card.getPregnantWomen() != null) {
            dto.setPregnancyRecordNo(card.getPregnantWomen().getPregnancyRecordNo());  // This is crucial
        }
        dto.setCid(card.getCid()); // Also ensure CID is set
        return dto;
    }


    public PregnancyCardDTO addPregnancyCard(PregnancyCardDTO dto){
        PregnantWomen pregnancyRecord = pregnantWomenRepository.findById(dto.getPregnancyRecordNo())
                .orElseThrow(() -> new RuntimeException("PregnancyRecord not found"));
        PregnancyCard card = convertToEntity(dto);
        card.setPregnantWomen(pregnancyRecord);
        PregnancyCard savedCard = repository.save(card);
        return convertToDTO(savedCard);
    }

    public List<PregnancyCardDTO> getAllPregnancyCards(){
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PregnancyCardDTO> getPregnancyCardsByPregnancyRecordNo(Integer pregnancyRecordNo) {
        return repository
                .findAllByPregnantWomen_PregnancyRecordNo(pregnancyRecordNo)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public PregnancyCardDTO updatePregnancyCardByPregnancyRecordNo(Integer pregnancyRecordNo, PregnancyCardDTO updatedDto) {
        Optional<PregnancyCard> existingCardOpt = repository.findByPregnantWomen_PregnancyRecordNo(pregnancyRecordNo);

        if (existingCardOpt.isPresent()) {
            PregnancyCard existingCard = existingCardOpt.get();

            Long cid = existingCard.getCid();
            PregnantWomen pregnancyRecord = existingCard.getPregnantWomen();

            modelMapper.map(updatedDto, existingCard);
            existingCard.setCid(cid);
            existingCard.setPregnantWomen(pregnancyRecord);

            PregnancyCard savedCard = repository.save(existingCard);
            return convertToDTO(savedCard);
        }

        return null;
    }

}
