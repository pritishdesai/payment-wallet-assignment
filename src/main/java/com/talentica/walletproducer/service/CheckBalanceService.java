package com.talentica.walletproducer.service;

import com.talentica.walletproducer.repository.UserWalletRepository;
import org.springframework.stereotype.Service;

@Service("checkBalanceService")
public class CheckBalanceService {

    private UserWalletRepository userWalletRepository;

    public CheckBalanceService(UserWalletRepository userWalletRepository) {
        this.userWalletRepository = userWalletRepository;
    }

    public Double getBalance(Long userId) {
        return userWalletRepository.getBalance(userId.toString());
    }

}
