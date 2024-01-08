package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UserTxnHstDto;
import com.talentica.walletproducer.entity.UserWalletTxHstDo;
import com.talentica.walletproducer.repository.UserWalletTxnHstRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service("TxnHstService")
public class TransactionHistoryService {

    @Autowired
    @Qualifier("UserTxnHstRepo")
    private UserWalletTxnHstRepository userWalletTxnHstRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserTxnHstDto> getTransactionHistory(Long userId){
        List<UserWalletTxHstDo> list =
                userWalletTxnHstRepository.getTransactionHistory(userId.toString());

        List<UserTxnHstDto> listDto = new ArrayList<>();

        for(UserWalletTxHstDo txHstDo : list){
            listDto.add(modelMapper.map(txHstDo, UserTxnHstDto.class));
        }

        return listDto;
    }

}
