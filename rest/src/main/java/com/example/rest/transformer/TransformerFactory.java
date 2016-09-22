package com.example.rest.transformer;


import com.example.rest.mediatype.InvalidMediaTypeException;
import com.example.rest.mediatype.MediaType;

public class TransformerFactory {

    private static volatile JsonTransformer jsonTransformer;

    private TransformerFactory() {
        super();
    }

    public static Transformer getInstance(final MediaType mediaType) {

        if (MediaType.JSON.equals(mediaType))
            return getJsonTransformer();
        else
            throw new InvalidMediaTypeException(String.format("Invalid media type %s.", mediaType));
    }

    private static JsonTransformer getJsonTransformer() {

        if (jsonTransformer == null) {
            synchronized (TransformerFactory.class) {
                if (jsonTransformer == null) {
                    jsonTransformer = new JsonTransformer();
                }
            }
        }

        return jsonTransformer;
    }
}
