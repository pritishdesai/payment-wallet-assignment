package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UserTransactionHistoryDto;
import com.talentica.walletproducer.dto.UsersDto;

import java.util.List;

public interface TransactionHistoryService {

    public List<UserTransactionHistoryDto> getTransactionHistory(UsersDto dto);
}
