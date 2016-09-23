package com.example.integration.message;

import com.example.domain.MessageDomain;
import com.example.domain.api.MessageClient;
import com.example.domain.api.MessageData;
import com.example.integration.AbstractRestClient;
import com.example.integration.utils.JsonUtils;
import org.springframework.stereotype.Component;

/**
 * author: dmitry.yakubovsky
 * date:   23/09/16
 */
@Component
public class MessageClientRest extends AbstractRestClient implements MessageClient {

    public void send(MessageDomain messageDomain) {
        String host = env.getProperty("integration.message.host");

        String requestBody = JsonUtils.toJson(new MessageRest(messageDomain));

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
