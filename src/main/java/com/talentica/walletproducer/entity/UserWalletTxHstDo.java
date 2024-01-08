package com.talentica.walletproducer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_wallet_tx_hst")
public class UserWalletTxHstDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userType;
    private String txnType; // Credit Or Debit
    private Double amount; //Amount that is credited or debited.
}
