package com.example.rest;

import com.example.rest.mediatype.MediaType;
import com.example.rest.utils.ErrorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

import static spark.Spark.exception;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class ExceptionMapper {


    private ExceptionMapper() {
        super();
    }

    static void setup() {

        exception(RuntimeException.class, new DefaultExceptionHandler(500));

    }

    private static final class DefaultExceptionHandler implements ExceptionHandler {

        private final static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

        private final int status;

        private final String message;

        private DefaultExceptionHandler(final int status) {
            super();
            this.status = status;
            this.message = null;
        }

        private DefaultExceptionHandler(final int status, final String message) {
            super();
            this.status = status;
            this.message = message;
        }

        @Override
        public void handle(final Exception exception, final Request request, final Response response) {


            logger.info("============== Exception mapper handled an Exception ==============\n {}.", exception);

            response.status(status);
            response.type(MediaType.JSON.toString());

            if (message != null)
                response.body(ErrorUtils.createError(message, MediaType.JSON));
            else
                response.body(ErrorUtils.createError(exception.getMessage(), MediaType.JSON));

        }
    }
}
