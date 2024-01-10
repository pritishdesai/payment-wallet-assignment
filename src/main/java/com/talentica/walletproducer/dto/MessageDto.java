package com.talentica.walletproducer.dto;

import lombok.*;

import java.util.Map;

@Data
public class MessageDto {

    private String topic;
    private String groupId;
    private Map<String,Object> message;
}
