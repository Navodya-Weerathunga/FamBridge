package com.edu.famBridge.service;

import com.edu.famBridge.dto.CancellationMessagedto;
import com.edu.famBridge.entity.CancelationMessage;
import jakarta.validation.Valid;

import java.util.List;

public interface CancellationMessageService {
    CancelationMessage addOrUpdateMessage(CancellationMessagedto cancellationMessagedto);
    void deleteMessage(Long id);
    CancellationMessagedto updateMessageByID(@Valid Long id , @Valid CancellationMessagedto cancellationMessagedto);
    CancellationMessagedto getLatestCancelationIdForUser(@Valid String nic);
    CancellationMessagedto getCancelationByCancelationID(@Valid Long id);
    List<CancelationMessage > getAllMessagesVirtual();
    List<CancelationMessage > getAllMessagesPhysical();
}
