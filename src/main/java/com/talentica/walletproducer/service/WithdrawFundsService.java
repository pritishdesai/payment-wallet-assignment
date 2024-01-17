package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.AddWithdrawFundsDto;

public interface WithdrawFundsService {

    public void publishWithdrawFunds(AddWithdrawFundsDto addWithdrawFundsDto);
}
