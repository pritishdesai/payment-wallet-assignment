package com.talentica.walletproducer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserTxnHstRepo")
public interface UserWalletTxnHstRepository extends JpaRepository<UserWalletTxnHstRepository,Long> {
}
