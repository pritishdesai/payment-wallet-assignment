package com.talentica.walletproducer.controller;

import com.talentica.walletproducer.dto.*;
import com.talentica.walletproducer.service.AddFundsServiceImpl;
import com.talentica.walletproducer.service.CheckBalanceServiceImpl;
import com.talentica.walletproducer.service.TransactionHistoryServiceImpl;
import com.talentica.walletproducer.service.TransferFundsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class PaymentWalletController {

    private final CheckBalanceServiceImpl checkBalanceService;
    private final TransactionHistoryServiceImpl transactionHistoryServiceImpl;
    private final TransferFundsServiceImpl transferFundsService;
    private final AddFundsServiceImpl addFundsService;

    @GetMapping("/balance")
    public ResponseEntity<UserWalletDto> getBalance(@RequestBody UsersDto usersDto) {
        UserWalletDto userWalletDto = new UserWalletDto();
        userWalletDto.setUserId(usersDto.getUserId());
        userWalletDto.setBalance(checkBalanceService.getBalance(usersDto));
        return new ResponseEntity<>(userWalletDto, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<UserTransactionHistoryDto>> getTransactionHistory(@RequestBody UsersDto usersDto) {
        List<UserTransactionHistoryDto> userTxnHstDtos =
                transactionHistoryServiceImpl.
                        getTransactionHistory(usersDto);
        return new ResponseEntity<>(userTxnHstDtos, HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity transferFunds(@RequestBody TransferRequestDto requestDto) {
        transferFundsService.publishTransferMessage(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public void addFunds(@RequestBody AddWithdrawFundsDto addWithdrawFundsDto){
        addFundsService.publishAddFunds(addWithdrawFundsDto);
    }
}
