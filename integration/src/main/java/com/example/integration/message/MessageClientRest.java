package com.example.integration.message;

import com.example.domain.api.MessageClient;
import com.example.domain.api.MessageData;
import com.example.integration.AbstractRestClient;
import com.example.integration.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageClientRest extends AbstractRestClient implements MessageClient {

    public void send(MessageData message) {

        String host = env.getProperty("integration.message.host");

        String requestBody = JsonUtils.toJson(new MessageRest(message));

        doPost(host + "/send-message-path", requestBody);
    }

    private class MessageRest {

        private final String createdAt;
        private final String body;

        public MessageRest(MessageData message) {
            this.body = message.body();
            this.createdAt = message.createdAt();
        }
    }
}
