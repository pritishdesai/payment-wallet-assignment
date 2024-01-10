package com.talentica.walletproducer.service.kafka;

import com.talentica.walletproducer.constants.AppConstants;
import com.talentica.walletproducer.dto.TransferRequestDto;
import com.talentica.walletproducer.service.TransferFundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WalletProducer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void addFundsPublish(){

    }

    public void withdrawFundsPublish(){

    }

    public void splitFundsPublish(){

    }

    public void transferFunds(TransferRequestDto transferRequestDto){

        Message<TransferRequestDto> message = MessageBuilder
                .withPayload(transferRequestDto)
                .setHeader(KafkaHeaders.TOPIC,AppConstants.TOPIC_TRANSFER_FUNDS)
                .build();

        kafkaTemplate.send(AppConstants.TOPIC_TRANSFER_FUNDS, message);

        log.info(String.format("Transfer Request Published between %s and %s",
                transferRequestDto.getSender().toString(),
                transferRequestDto.getReceiver().toString()));
    }

}
