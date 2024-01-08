package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserWalletTxHstDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserTxnHstRepo")
public interface UserWalletTxnHstRepository extends JpaRepository<UserWalletTxHstDo,Long> {

    @Query(nativeQuery = true,value = "select * from user_wallet_tx_hst where user_id = :userId")
    public List<UserWalletTxHstDo> getTransactionHistory(String userId);

}
