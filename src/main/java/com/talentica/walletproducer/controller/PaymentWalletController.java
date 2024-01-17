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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<ResponseDto> transferFunds(@RequestBody TransferRequestDto requestDto) {
        requestDto.setTransactionId(UUID.randomUUID().toString());
        transferFundsService.publishTransferMessage(requestDto);
        ResponseDto responseDto =
                ResponseDto
                        .builder()
                        .httpStatus(HttpStatus.OK)
                        .transactionId(requestDto.getTransactionId())
                        .message("Transfer Funds Request Published Successfully")
                        .date(LocalDate.now())
                        .build();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseDto> addFunds(@RequestBody AddWithdrawFundsDto addWithdrawFundsDto){
        addWithdrawFundsDto.setTransactionId(UUID.randomUUID().toString());
        addFundsService.publishAddFunds(addWithdrawFundsDto);
        ResponseDto responseDto =
                ResponseDto
                        .builder()
                        .httpStatus(HttpStatus.OK)
                        .transactionId(addWithdrawFundsDto.getTransactionId())
                        .message("Add Funds Request Published Successfully")
                        .date(LocalDate.now())
                        .build();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
