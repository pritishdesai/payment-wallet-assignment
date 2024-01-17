package com.talentica.walletproducer.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeDto {

    private String userName;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvv;
    private String stripeToken;
    private Double amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Map<String,Object> additionalInfo = new HashMap<>();

}
