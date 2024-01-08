package com.talentica.walletproducer.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.talentica.walletproducer.dto.UserTxnHstDto;
import com.talentica.walletproducer.dto.UserWalletDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.service.CheckBalanceService;
import com.talentica.walletproducer.service.TransactionHistoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class PaymentWalletController {

    @Autowired
    @Qualifier("checkBalanceService")
    private CheckBalanceService checkBalanceService;

    @Autowired
    @Qualifier("TxnHstService")
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/balance")
    public ResponseEntity<UserWalletDto> getBalance(@RequestBody UsersDto usersDto){
        UserWalletDto userWalletDto = new UserWalletDto();
        userWalletDto.setUserId(usersDto.getUserId());
        userWalletDto.setBalance(checkBalanceService.getBalance(usersDto.getUserId()));
        return new ResponseEntity<>(userWalletDto, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<UserTxnHstDto>> getTransactionHistory(@RequestBody UsersDto usersDto){
        List<UserTxnHstDto> userTxnHstDtos =
                transactionHistoryService.
                        getTransactionHistory(usersDto.getUserId());
        return new ResponseEntity<>(userTxnHstDtos,HttpStatus.OK);

    }
}
