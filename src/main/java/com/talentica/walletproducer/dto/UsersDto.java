package com.talentica.walletproducer.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private String userId;
    private String userName;
    private String userType;
    private List<StripeDto> stripeDetails;

}
