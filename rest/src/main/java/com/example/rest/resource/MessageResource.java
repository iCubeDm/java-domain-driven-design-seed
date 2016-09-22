package com.example.rest.resource;

import com.example.domain.MessageService;

import static spark.Spark.post;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class MessageResource {

    public static void setupEndpoints(MessageService messageService) {

        post("/messages", (request, response) -> {

            String requestBody = request.body();

            messageService.process(requestBody);
            response.status(200);
            return "";
        });
    }
}
