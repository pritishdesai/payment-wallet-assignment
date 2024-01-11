package com.talentica.walletproducer.mapper;

import com.talentica.walletproducer.dto.UserTransactionHistoryDto;
import com.talentica.walletproducer.entity.UserWalletTransactionHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWalletTransactionHistoryMapper {

    UserWalletTransactionHistoryMapper USER_WALLET_TRANSACTION_HISTORY_MAPPER =
            Mappers.getMapper(UserWalletTransactionHistoryMapper.class);

    UserWalletTransactionHistoryEntity convertDtoToEntity(UserTransactionHistoryDto userTransactionHistoryDto);

    UserTransactionHistoryDto convertEntityToDto(UserWalletTransactionHistoryEntity userWalletTransactionHistoryEntity);
}
