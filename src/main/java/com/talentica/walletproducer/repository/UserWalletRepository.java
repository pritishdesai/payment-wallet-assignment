package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserWalletRepo")
public interface UserWalletRepository extends JpaRepository<UserWalletEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT BALANCE FROM USER_WALLET WHERE USER_ID = :userId")
    public String getBalance(String userId);
}
