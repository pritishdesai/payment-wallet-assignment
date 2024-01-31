package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.repository.UserWalletRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("checkBalanceService")
@RequiredArgsConstructor
public class CheckBalanceServiceImpl implements CheckBalanceService{

    private final UserWalletRepository userWalletRepository;

    @Override
    public String getBalance(@NonNull UsersDto usersDto) {
        return userWalletRepository.getBalance(usersDto.getUserId());
    }

}
