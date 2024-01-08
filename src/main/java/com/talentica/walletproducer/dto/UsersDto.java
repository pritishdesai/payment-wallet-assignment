package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long userId;
    private String userName;
    private String userType;
    private List<StripeDto> stripeDetails;

}
