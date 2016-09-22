package com.example.rest.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

import static spark.Spark.*;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class CustomFilters {
    private static final Logger log = LoggerFactory.getLogger(CustomFilters.class);
    private static final String HEADER_REQUEST_ID = "TX-ID";


    public static void beforeFilters() {
        beforeTx();
        beforeLog();
        cors();
        contentType();
    }

    public static void afterFilters() {
        afterCleanup();
    }

    private static void beforeTx() {

        before((req, res) -> {
            String requestId = req.headers(HEADER_REQUEST_ID);

            if (requestId == null) {
                requestId = "REQUEST-" + UUID.randomUUID().toString();
            }

            res.header(HEADER_REQUEST_ID, requestId);

            MDC.put(HEADER_REQUEST_ID, requestId);
        });
    }

    private static void beforeLog() {

        before((req, res) -> log.info("BEGIN - path={}", req.pathInfo()));
    }

    private static void cors() {

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
        });
    }

    private static void contentType() {

        before((request, response) -> response.type("application/json"));
    }

    private static void afterCleanup() {

        after((req, res) -> MDC.clear());
    }

}
