package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private HttpStatus httpStatus;
    private String transactionId;
    private String message;
    private LocalDate date;
}
