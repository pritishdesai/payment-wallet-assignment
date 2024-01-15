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
                userWalletTxnHstRepository.getTransactionHistory(dto.getUserId().toString());

        List<UserTransactionHistoryDto> listDto = new ArrayList<>();

        for(UserWalletTransactionHistoryEntity txHstDo : list){
            listDto.add(modelMapper.map(txHstDo, UserTransactionHistoryDto.class));
        }
        return listDto;
    }

}
