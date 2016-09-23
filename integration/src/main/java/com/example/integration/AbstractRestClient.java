package com.example.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * author: dmitry.yakubovsky
 * date:   21/03/16
 */
public abstract class AbstractRestClient {

    /**
     * Logger
     */
    protected Logger logger;

    @Autowired
    protected Environment env;

    /**
     * HTTP Client
     */
    protected final Client client;

    /**
     * Constructor
     */
    public AbstractRestClient() {
        this.client = ClientBuilder.newClient();
    }

    /**
     * Constructor
     *
     * @param client any Jax-Rs compliant client
     */
    public AbstractRestClient(Client client) {
        this.client = client;
    }

    /**
     * Post initializers
     */
    {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * POST request
     *
     * @param url         request URL
     * @param requestBody serialized request body
     * @return raw response body
     */
    protected String doPost(String url, String requestBody) {

        logger.info("Registered POST Request to {}", url);
        logger.debug("With body -> {} \n", requestBody);

        final Response res = client.target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(requestBody), Response.class);

        res.bufferEntity();
        String responseBody = res.readEntity(String.class);
        logger.info("Response with code {} \n",res.getStatus());
        logger.debug("And body -> {} \n", responseBody);

        checkError(res);

        return responseBody;
    }

    /**
     * GET request
     *
     * @param url request URL with params
     * @return raw response body
     */
    protected String doGet(String url) {

        logger.debug("Registered GET Request -> {} \n", url);

        final Response res = client.target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class);

        res.bufferEntity();
        String responseBody = res.readEntity(String.class);
        logger.info("Response with code {} \n",res.getStatus());
        logger.debug("Response -> {} \n", responseBody);

        checkError(res);

        return responseBody;
    }

    /**
     * Checks response for status codes
     *
     * @param res response object for validation
     */
    protected void checkError(Response res) {
        if (res.getStatus() < 300) {
            return;
        }

        if (res.getStatus() == 404) {
            throw new NotFoundException("No results found.");
        }

        // TODO custom exceptions here
        // final String responseBody = res.readEntity(String.class);
    }
}
