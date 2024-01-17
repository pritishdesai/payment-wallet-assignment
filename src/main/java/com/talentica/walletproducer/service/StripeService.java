package com.talentica.walletproducer.service;

import com.stripe.exception.StripeException;
import com.talentica.walletproducer.dto.StripeTokenDto;

public interface StripeService {

    public StripeTokenDto createCardToken(StripeTokenDto stripeTokenDto);
}
