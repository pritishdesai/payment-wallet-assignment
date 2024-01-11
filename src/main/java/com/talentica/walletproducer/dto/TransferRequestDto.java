package com.talentica.walletproducer.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {

        private String amount;
        private UsersDto sender;
        private UsersDto receiver;
}
