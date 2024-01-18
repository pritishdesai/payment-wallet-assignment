package com.talentica.walletproducer.validation;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import com.talentica.walletproducer.repository.UserRepository;
import com.talentica.walletproducer.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserValidationUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWalletRepository userWalletRepository;

    public boolean checkIfUserValid(UsersDto usersDto){
        Optional<UserEntity> user = userRepository.findById(Long.parseLong(usersDto.getUserId()));
        return user.isPresent();
    }

    public boolean checkIfBalanceAvailable(UsersDto usersDto,String amount){
        String balance = userWalletRepository.getBalance(usersDto.getUserId());
        return BigDecimal.valueOf(Double.parseDouble(balance)).compareTo(BigDecimal.valueOf(Double.parseDouble(amount))) >= 0;

    }

    public boolean checkIfUserIsMerchant(UsersDto usersDto){
        Optional<UserEntity> user = userRepository.findById(Long.parseLong(usersDto.getUserId()));
        return user
                .orElseGet(UserEntity::new)
                .getUserType()
                .equalsIgnoreCase("Merchant");
    }

    public boolean checkIfUserValidByUserId(String userId){
        Optional<UserEntity> user = userRepository.findById(Long.parseLong(userId));
        return user.isPresent();

    }


}
