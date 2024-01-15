package com.talentica.walletproducer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionHistoryDto {

    private String Id;
    private String userId;
    private String userType;
    private String txnType;
    private String amount;
}
