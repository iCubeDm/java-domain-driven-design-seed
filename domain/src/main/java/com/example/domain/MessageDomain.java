package com.example.domain;

import com.example.domain.api.MessageData;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class MessageDomain implements MessageData {

    private final String body;

    private final String createdAt;

    public MessageDomain(String messageContent) {
        this.body = messageContent.trim();
        this.createdAt = ZonedDateTime.now().format(ISO_LOCAL_DATE_TIME);
    }

    public String body(){
        return body;
    }

    public String createdAt(){
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s", createdAt(), body());
    }
}
