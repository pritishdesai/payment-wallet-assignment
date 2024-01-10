package com.talentica.walletproducer.mapper;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UsersDto convertUserEntityToUsersDto(UserEntity userEntity);
}
