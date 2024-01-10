package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWithdrawFundsDto {

    private String userId;
    private String amount;
    private StripeDto stripeDetails;
    private String requestType; //Add Or Withdraw (A/W)
}
