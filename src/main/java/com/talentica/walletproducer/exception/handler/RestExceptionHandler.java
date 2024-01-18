package com.talentica.walletproducer.exception.handler;

import com.stripe.exception.StripeException;
import com.talentica.walletproducer.dto.ExceptionDto;
import com.talentica.walletproducer.exception.InsufficientBalanceException;
import com.talentica.walletproducer.exception.InvalidStripeCardException;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.exception.UserNotAMerchantException;
import org.apache.coyote.Response;
import org.apache.kafka.common.security.oauthbearer.internals.secured.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(value = {InsufficientBalanceException.class})
    public ResponseEntity<ExceptionDto> handleInsufficientBalanceException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "Insufficient Balance in Wallet!",
                        new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoUserFoundException.class})
    public ResponseEntity<ExceptionDto> handleNoUserFoundException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "No Such User Found!",
                        new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {StripeException.class})
    public ResponseEntity<ExceptionDto> handleStripeException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "Stripe Exception",
                        new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidStripeCardException.class})
    public ResponseEntity<ExceptionDto> handleInvalidStripeCardException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "Invalid Stripe Card Details!!",
                        new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotAMerchantException.class})
    public ResponseEntity<ExceptionDto> handleUserNotAMerchantException(){
        return new ResponseEntity<>(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        "User Is Not A Merchant. Only Merchants can split funds!!",
                        new Date()),
                HttpStatus.BAD_REQUEST);

    }



}
