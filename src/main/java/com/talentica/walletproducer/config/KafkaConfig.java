package com.talentica.walletproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${TOPIC_ADD_FUNDS}")
    private String topicAddFunds;

    @Value("${TOPIC_WITHDRAW_FUNDS}")
    private String topicWithdrawFunds;

    @Value("${TOPIC_TRANSFER_FUNDS}")
    private String topicTransferFunds;

    @Value("${TOPIC_SPLIT_FUNDS}")
    private String topicSplitFunds;

    @Bean
    public NewTopic addFundsTopic(){
        return TopicBuilder
                .name("payment-wallet-add-funds")
                .build();
    }

    @Bean
    public NewTopic withdrawFundsTopic(){
        return TopicBuilder
                .name("payment-wallet-withdraw-funds")
                .build();
    }

    @Bean
    public NewTopic transferFundsTopic(){
        return TopicBuilder
                .name("payment-wallet-transfer-funds")
                .build();
    }

    @Bean
    public NewTopic splitFundsTopic(){
        return TopicBuilder
                .name("payment-wallet-split-funds")
                .build();
    }

}
