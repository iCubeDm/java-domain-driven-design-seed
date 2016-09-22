package com.example.rest.utils;

import com.example.rest.mediatype.MediaType;
import org.apache.commons.lang3.StringUtils;

/**
 * author: dmitry.yakubovsky
 * date:   13/08/16
 */
public class ErrorUtils {

    public static String createError(final String message, final MediaType resultType) {

        return new Error(message).toString();
    }

    private static final class Error {

        @SuppressWarnings("unused")
        private final String message;

        private Error(final String message) {
            super();
            if (StringUtils.isBlank(message))
                this.message = "Error message is not specified.";
            else if (message.charAt(0) == '{' || message.charAt(0) == '[') // implemented for json objects
                this.message = message;
            else
                this.message = String.format("\"%s\"", message);
        }

        @Override
        public String toString() {
            return String.format("{\"error\": %s}", message);
        }
    }
}
