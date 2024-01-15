package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UsersDto;

public interface CheckBalanceService {

    public String getBalance(UsersDto usersDto);
}
