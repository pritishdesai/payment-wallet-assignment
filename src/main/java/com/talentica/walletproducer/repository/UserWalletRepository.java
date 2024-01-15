package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserWalletRepo")
public interface UserWalletRepository extends JpaRepository<UserWalletEntity, Long> {

    @Query(nativeQuery = true, value = "select balance from user_wallet where user_id = :userId")
    public String getBalance(String userId);
}
