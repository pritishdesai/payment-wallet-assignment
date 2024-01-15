package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepo")
public interface UserRepository  extends JpaRepository<UserEntity,Long> {

}
