package com.talentica.walletproducer.controller;

import com.talentica.walletproducer.dto.UserTxnHstDto;
import com.talentica.walletproducer.dto.UserWalletDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.service.CheckBalanceService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class PaymentWalletController {

    @Autowired
    @Qualifier("checkBalanceService")
    private CheckBalanceService checkBalanceService;

    @GetMapping("/balance")
    public UserWalletDto getBalance(@RequestBody UsersDto usersDto){
        UserWalletDto userWalletDto = new UserWalletDto();
        userWalletDto.setUserId(usersDto.getUserId());
        userWalletDto.setBalance(checkBalanceService.getBalance(usersDto.getUserId()));
        return userWalletDto;
    }

    public List<UserTxnHstDto> getTransactionHistory(@RequestBody UsersDto usersDto){
        return null;
    }

}
