package com.talentica.walletproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private HttpStatus httpStatus;
    private String message;
    private Date date;
}
