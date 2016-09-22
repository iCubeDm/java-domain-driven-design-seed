package com.example.rest;

import com.example.domain.DomainService;
import com.example.domain.MessageService;
import com.example.rest.resource.MessageResource;

import java.util.Map;

import static com.example.rest.filters.CustomFilters.afterFilters;
import static com.example.rest.filters.CustomFilters.beforeFilters;
import static spark.Spark.*;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class ExampleRestInitializer {

    public static void initialize(Map<Class, DomainService> services) {

        setupStaticFileLocation();

        port(8081);

        beforeFilters();

        ExceptionMapper.setup();

        setupEndpoints(services);

        afterFilters();

        awaitInitialization();

    }

    static void setupEndpoints(Map<Class, DomainService> services) {

        options("/*", (req, res) -> "pong");

            final MessageService messageService = (MessageService) services.get(MessageService.class);

            MessageResource.setupEndpoints(messageService);
    }

    static void setupStaticFileLocation() {
        staticFileLocation("/public");
    }
}
