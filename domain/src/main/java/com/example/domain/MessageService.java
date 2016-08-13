package com.example.domain;

import com.example.domain.api.MessageClient;
import com.example.domain.repository.MessageRepository;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class MessageService implements DomainService {

    private final MessageRepository messageRepository;

    private final MessageClient messageClient;

    public MessageService(MessageRepository messageRepository, MessageClient messageClient) {
        this.messageRepository = messageRepository;
        this.messageClient = messageClient;
    }

    public void process(String messageContent) {

        Message message = new Message(messageContent);

        System.out.println(message);

        messageRepository.save(message);
        messageClient.send(message);
    }
}
