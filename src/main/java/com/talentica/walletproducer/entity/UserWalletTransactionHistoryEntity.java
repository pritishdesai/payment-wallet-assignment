package com.talentica.walletproducer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet_tx_hst")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWalletTransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userType;
    private String transactionType; // Credit Or Debit
    private BigDecimal amount; //Amount that is credited or debited.
    private String transactionId;

    private LocalDateTime actionDate;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
}
