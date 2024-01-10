package com.talentica.walletproducer.exception.handler;

import com.talentica.walletproducer.dto.ExceptionDto;
import com.talentica.walletproducer.exception.InsufficientBalanceException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {InsufficientBalanceException.class})
    public ResponseEntity<ExceptionDto> handleInsufficientBalanceException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "Insufficient Balance in Wallet!",
                        new Date()),
                HttpStatus.BAD_REQUEST);
    }

}
