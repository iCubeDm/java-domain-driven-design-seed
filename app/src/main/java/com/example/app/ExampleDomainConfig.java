package com.example.app;

import com.example.domain.MessageService;
import com.example.domain.api.MessageClient;
import com.example.domain.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
@Configuration
public class ExampleDomainConfig {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageClient messageClient;

    @Bean
    public MessageService messageService() {
        return new MessageService(messageRepository, messageClient);
    }
}
