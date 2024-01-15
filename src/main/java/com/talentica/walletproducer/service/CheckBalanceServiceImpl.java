package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.repository.UserWalletRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service("checkBalanceService")
public class CheckBalanceServiceImpl implements CheckBalanceService{

    private UserWalletRepository userWalletRepository;

    public CheckBalanceServiceImpl(UserWalletRepository userWalletRepository) {
        this.userWalletRepository = userWalletRepository;
    }

    @Override
    public String getBalance(@NonNull UsersDto usersDto) {
        return userWalletRepository.getBalance(usersDto.getUserId());
    }

}
