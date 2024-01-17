package com.talentica.walletproducer.repository;

import com.talentica.walletproducer.entity.UserWalletTransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserTxnHstRepo")
public interface UserWalletTxnHstRepository extends JpaRepository<UserWalletTransactionHistoryEntity,Long> {


    public List<UserWalletTransactionHistoryEntity> findAllByUserIdOrderByIdDesc(Long userId);

}
