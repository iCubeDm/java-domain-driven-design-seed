package com.example.domain;

import com.example.domain.api.MessageClient;
import com.example.domain.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class MessageDomainServiceTest {

    @Mock
    private MessageRepository mockRepository;

    @Mock
    private MessageClient mockClient;

    @InjectMocks
    MessageService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessMessageSuccessFlow() {
        String receivedMessageContent = "Hello, World!";
        ArgumentCaptor<MessageDomain> repositoryMessageCaptor = ArgumentCaptor.forClass(MessageDomain.class);
        ArgumentCaptor<MessageDomain> clientMessageCaptor = ArgumentCaptor.forClass(MessageDomain.class);

        doNothing().when(mockRepository).save(repositoryMessageCaptor.capture());
        doNothing().when(mockClient).send(clientMessageCaptor.capture());

        service.process(receivedMessageContent);

        MessageDomain capturedRepositoryMessageDomain = repositoryMessageCaptor.getValue();
        MessageDomain capturedClientMessageDomain = clientMessageCaptor.getValue();
        assertSame(capturedClientMessageDomain, capturedRepositoryMessageDomain);
        assertEquals(receivedMessageContent.trim(), capturedClientMessageDomain.body());

    }
}
