package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTxnHstDto {

    private Long Id;
    private Long userId;
    private String userType;
    private String txnType;
    private Double amount;
}
