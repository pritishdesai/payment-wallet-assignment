package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.TransferRequestDto;
import com.talentica.walletproducer.dto.UserWalletDto;
import com.talentica.walletproducer.exception.InsufficientBalanceException;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.validation.UserValidationUtil;
import com.talentica.walletproducer.service.kafka.WalletProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transferFundsServiceImpl")
@Slf4j
public class TransferFundsServiceImpl implements TransferFundsService {

    @Autowired
    private UserValidationUtil userValidationUtil;

    @Autowired
    private WalletProducer walletProducer;

    @Override
    public void publishTransferMessage(TransferRequestDto transferRequestDto) {

        if (
                userValidationUtil.checkIfUserValid(transferRequestDto.getSender()) &&
                        userValidationUtil.checkIfUserValid(transferRequestDto.getReceiver())
        ) {
            log.info((String.format("TransferFundsServiceImpl::publishTransferMessage -> User Validation Check Successful for %s & %s",
                    transferRequestDto.getSender().toString(),
                    transferRequestDto.getReceiver().toString())));
            if (userValidationUtil.checkIfBalanceAvailable(transferRequestDto.getSender(),
                    transferRequestDto.getAmount())) {
                log.info(String.format("TransferFundsServiceImpl::publishTransferMessage -> User Wallet Balance Verification Successful for Sender %s",
                        transferRequestDto.getSender()));
                walletProducer.transferFunds(transferRequestDto);
            } else {
                throw new InsufficientBalanceException("Insufficient Balance in Wallet");
            }
        } else {
            throw new NoUserFoundException("User does not exist");
        }
    }



}
