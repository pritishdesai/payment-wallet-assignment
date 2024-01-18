package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.SplitFundsDto;
import com.talentica.walletproducer.dto.TransferRequestDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import com.talentica.walletproducer.exception.InsufficientBalanceException;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.exception.UserNotAMerchantException;
import com.talentica.walletproducer.mapper.UserMapper;
import com.talentica.walletproducer.repository.UserRepository;
import com.talentica.walletproducer.service.kafka.WalletProducer;
import com.talentica.walletproducer.validation.UserValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SplitFundsServiceImpl implements SplitFundsService {

    private final UserRepository userRepository;
    private final UserValidationUtil userValidationUtil;
    private final WalletProducer walletProducer;

    private Double amount = Double.parseDouble("0");
//    private boolean userCheckFlag = false;

    @Override
    public void splitFunds(SplitFundsDto splitFundsDto) {

        UsersDto merchantDto =
                UserMapper.USER_MAPPER.convertUserEntityToUsersDto(
                        userRepository
                                .findById(Long.parseLong(splitFundsDto.getMerchantUserId()))
                                .orElseGet(UserEntity::new)
                );

        if (userValidationUtil.checkIfUserValid(merchantDto)) {
            if (userValidationUtil.checkIfUserIsMerchant(merchantDto)) {
                for (String splitAmount : splitFundsDto.getCustomerSplitDetails().values()) {
                    if (!StringUtils.isEmpty(splitAmount)) {
                        amount += Double.parseDouble(splitAmount);
                    }
                }
                if (userValidationUtil.checkIfBalanceAvailable(merchantDto, String.valueOf(amount))) {
                    walletProducer.splitFundsPublish(splitFundsDto);

                    for (Map.Entry<String, String> splitCustomer : splitFundsDto.getCustomerSplitDetails().entrySet()) {
                        if (userValidationUtil.checkIfUserValidByUserId(splitCustomer.getKey())) {
                            UsersDto receiverDto =
                                    UserMapper.USER_MAPPER.convertUserEntityToUsersDto(
                                            userRepository
                                                    .findById(Long.parseLong(splitCustomer.getKey()))
                                                    .get());


                            TransferRequestDto transferRequestDto =
                                    TransferRequestDto
                                            .builder()
                                            .amount(splitFundsDto.getCustomerSplitDetails().get(splitCustomer.getKey()))
                                            .sender(merchantDto)
                                            .receiver(receiverDto)
                                            .transactionId(UUID.randomUUID().toString())
                                            .build();

                            walletProducer.transferFunds(transferRequestDto);
                        } else {
                            throw new NoUserFoundException("No Such User Found!!");
                        }
                    }
                } else {
                    throw new InsufficientBalanceException("Insufficient Balance In Wallet!!");
                }
            } else {
                throw new UserNotAMerchantException("User Not A Merchant!");
            }
        } else {
            throw new NoUserFoundException("No Such User Found!!");
        }
    }
}
