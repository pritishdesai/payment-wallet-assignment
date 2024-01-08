package com.talentica.walletproducer.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletProducer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void addFundsPublish(){

    }

    public void withdrawFundsPublish(){

    }

    public void splitFundsPublish(){

    }

    public void transferFunds(){

    }

}
