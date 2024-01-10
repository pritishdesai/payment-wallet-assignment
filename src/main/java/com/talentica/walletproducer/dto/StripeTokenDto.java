package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeTokenDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvv;
    private String token;
    private String username;
    private boolean successFlag;

    public StripeTokenDto(String cardNumber, String expMonth, String expYear, String cvv) {
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvv = cvv;
    }
}
