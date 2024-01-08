package com.talentica.walletproducer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_wallet")
@Data
public class UserWalletDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWalletId;
    private Long userId;
    private Double balance;
}
