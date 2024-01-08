package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserWalletDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserWalletRepo")
public interface UserWalletRepository extends JpaRepository<UserWalletDo, Long> {


    @Query(nativeQuery = true,value = "SELECT BALANCE FROM USER_WALLET WHERE USER_ID = :userId")
    public Double getBalance(String userId);
}
