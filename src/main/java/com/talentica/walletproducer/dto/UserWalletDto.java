package com.talentica.walletproducer.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWalletDto {

    private String userId;
    private String balance;


}
