package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitFundsDto {

    private String merchantUserId;
    private Map<String,String> customerSplitDetails;
    private String transactionId;
}
