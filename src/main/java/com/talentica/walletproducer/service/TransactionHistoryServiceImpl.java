package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UserTransactionHistoryDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserWalletTransactionHistoryEntity;
import com.talentica.walletproducer.repository.UserWalletTxnHstRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("TxnHstService")
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

    @Autowired
    @Qualifier("UserTxnHstRepo")
    private UserWalletTxnHstRepository userWalletTxnHstRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserTransactionHistoryDto> getTransactionHistory(UsersDto dto){
        List<UserWalletTransactionHistoryEntity> list =
                userWalletTxnHstRepository.findAllByUserIdOrderByIdDesc(Long.parseLong(dto.getUserId()));

        List<UserTransactionHistoryDto> listDto = new ArrayList<>();

        for(UserWalletTransactionHistoryEntity txHstEntity: list){
            listDto.add(UserTransactionHistoryDto
                    .builder()
                    .Id(String.valueOf(txHstEntity.getId()))
                    .userId(String.valueOf(txHstEntity.getUserId()))
                    .userType(txHstEntity.getUserType())
                    .amount(String.valueOf(txHstEntity.getAmount()))
                    .txnType(txHstEntity.getTransactionType())
                    .transactionId(txHstEntity.getTransactionId())
                    .build());
        }
        return listDto;
    }

}
