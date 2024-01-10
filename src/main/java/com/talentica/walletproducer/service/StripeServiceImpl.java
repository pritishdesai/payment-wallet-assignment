package com.talentica.walletproducer.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import com.talentica.walletproducer.dto.StripeTokenDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeServiceImpl implements StripeService {

    @Value("${api.stripe.key}")
    private String apiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = apiKey;
    }

    @Override
    public StripeTokenDto createCardToken(StripeTokenDto stripeTokenDto) {
        try {
            Map<String,Object> card = new HashMap<>();
            card.put("number",stripeTokenDto.getCardNumber());
            card.put("exp_month",Integer.parseInt(stripeTokenDto.getExpMonth()));
            card.put("exp_year",Integer.parseInt(stripeTokenDto.getExpYear()));
            card.put("cvc",stripeTokenDto.getCvv());

            Map<String,Object> params = new HashMap<>();
            params.put("card",card);

            Token token = Token.create(params);
            if(token != null && token.getId() != null){
                stripeTokenDto.setSuccessFlag(true);
                stripeTokenDto.setToken(token.getId());
            }

        }catch (StripeException e){
            log.error(String.format("StripeServiceImpl::createCardToken -> %s",e.toString()));
        }

        return stripeTokenDto;
    }
}
