package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.CancellationMessagedto;
import com.edu.famBridge.entity.CancelationMessage;
import com.edu.famBridge.repository.CancelationMessageRepository;
import com.edu.famBridge.service.CancellationMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelationMessageServiceImpl implements CancellationMessageService {
    @Autowired
    private final CancelationMessageRepository cancelationMessageRepository;
    @Autowired
    private ModelMapper modelMapper;

    //Create Message
    @Override
    public CancelationMessage addOrUpdateMessage(CancellationMessagedto cancellationMessagedto) {
        CancelationMessage message = new CancelationMessage();
        message.setDate(cancellationMessagedto.getDate());
        message.setMeetingTime(cancellationMessagedto.getMeetingTime());
        message.setMeetingDate(cancellationMessagedto.getMeetingDate());
        message.setMessage(cancellationMessagedto.getMessage());
        message.setEmail(cancellationMessagedto.getEmail());
        message.setUserName(cancellationMessagedto.getUserName());
        message.setType(cancellationMessagedto.getType());
        message.setNic(cancellationMessagedto.getNic());
        message.setMethod(cancellationMessagedto.getMethod());
        message.setMeetingId(cancellationMessagedto.getMeetingId());
        message.setMidwifeName(cancellationMessagedto.getMidwifeName());

        return cancelationMessageRepository.save(message);
    }
    @Override// Delete Message
    public void deleteMessage(Long id) {
        cancelationMessageRepository.deleteById(id);
    }

    //Update Message with relevant Id
    @Override
    public CancellationMessagedto updateMessageByID(@Valid Long id , @Valid CancellationMessagedto cancellationMessagedto){
        CancelationMessage message=cancelationMessageRepository.updateMessageByID(id);
        cancelationMessageRepository.save(modelMapper.map(cancellationMessagedto, CancelationMessage.class));
        return cancellationMessagedto;
    }

    //Get Latest Id for message using nic
    @Override
    public CancellationMessagedto getLatestCancelationIdForUser(@Valid String nic) {
        Long latestCancelationId =cancelationMessageRepository.findLatestCancelationIdforUser(nic);
        if (latestCancelationId != null) {
            Optional<CancelationMessage> cancelOptional = cancelationMessageRepository.findById(latestCancelationId);
            if (cancelOptional.isPresent()) {
                return modelMapper.map(cancelOptional.get(), CancellationMessagedto.class);
            }
        }
        return null;
    }

    //Get cancelation by id
    @Override
    public CancellationMessagedto getCancelationByCancelationID(@Valid Long id){
        CancelationMessage cancel=cancelationMessageRepository.getCancelationByCancelationID(id);
        return modelMapper.map(cancel,CancellationMessagedto.class);
    }

    @Override// Get all messages
    public List<CancelationMessage> getAllMessagesVirtual() {
        return cancelationMessageRepository.findAllVirtal();
    }


    @Override// Get all messages
    public List<CancelationMessage > getAllMessagesPhysical() {
        return cancelationMessageRepository.findAllPhysical();
    }

}
