package com.talentica.walletproducer.service.kafka;

import com.talentica.walletproducer.constants.AppConstants;
import com.talentica.walletproducer.dto.AddWithdrawFundsDto;
import com.talentica.walletproducer.dto.TransferRequestDto;
import com.talentica.walletproducer.service.TransferFundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${TOPIC_ADD_FUNDS}")
    private String topicAddFunds;

    @Value("${TOPIC_WITHDRAW_FUNDS}")
    private String topicWithdrawFunds;

    @Value("${TOPIC_TRANSFER_FUNDS}")
    private String topicTransferFunds;

    @Value("${TOPIC_SPLIT_FUNDS}")
    private String topicSplitFunds;

    public void addFundsPublish(AddWithdrawFundsDto addWithdrawFundsDto){
        Message<AddWithdrawFundsDto> message = MessageBuilder
                .withPayload(addWithdrawFundsDto)
                .setHeader(KafkaHeaders.TOPIC,AppConstants.TOPIC_ADD_FUNDS)
                .build();

        kafkaTemplate.send(AppConstants.TOPIC_ADD_FUNDS,
                message);

        log.info(String.format("WalletProducer::addFundsPublish -> " +
                "Add Funds Request Published For Amount %s ,User %s",
                addWithdrawFundsDto.getAmount(),
                addWithdrawFundsDto.getUserId()));
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
