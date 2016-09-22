package com.example.rest.mediatype;

import static java.lang.String.format;

public enum MediaType {

    JSON("application/json");

    private final String mediaType;

    MediaType(final String mediaType) {
        this.mediaType = mediaType;
    }

    public static MediaType of(String mediaType) {

        mediaType = (mediaType != null) ? mediaType : "";

        if (mediaType.toLowerCase().contains("application/json"))
            return JSON;
        else
            throw new InvalidMediaTypeException(format("Invalid media type %s.", mediaType));
    }

    @Override
    public String toString() {
        return mediaType;
    }
}
