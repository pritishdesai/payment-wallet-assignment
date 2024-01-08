package com.talentica.walletproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

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
