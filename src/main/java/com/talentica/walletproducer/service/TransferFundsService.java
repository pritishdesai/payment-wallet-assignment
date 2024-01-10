package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.TransferRequestDto;

public interface TransferFundsService {

    public void publishTransferMessage(TransferRequestDto transferRequestDto);
}
