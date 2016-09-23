package com.example.integration.message;

import com.example.domain.api.MessageClient;
import com.example.domain.api.MessageData;
import com.example.integration.TestIntegrationConfig;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.ZonedDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestIntegrationConfig.class)
public class MessageClientRestTest {

    private static final int MESSAGE_CLIENT_PORT = 30666;

    @Rule
    public WireMockRule messageClientMock = new WireMockRule(MESSAGE_CLIENT_PORT);

    @Autowired
    MessageClient messageClient;

    @Test
    public void testSendMessage() throws IOException {

        MessageData message = new TestMessage();

        givenThat(
                post(urlEqualTo("/send-message-path"))
                        .withRequestBody(containing("TEST_MESSAGE_BODY"))
                        .withRequestBody(containing("2007-09-15T00:00:00Z"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}")
                        )
        );

        messageClient.send(message);
    }

    private class TestMessage implements MessageData {

        public String body() {
            return "TEST_MESSAGE_BODY";
        }

        public String createdAt() {
            return ZonedDateTime.of(2007, 9, 15, 0, 0, 0, 0, UTC).format(ISO_DATE_TIME);
        }
    }
}
