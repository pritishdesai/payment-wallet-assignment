package com.talentica.walletproducer.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDto {

        private String amount;
        private UsersDto sender;
        private UsersDto receiver;
        private String transactionId;
}
