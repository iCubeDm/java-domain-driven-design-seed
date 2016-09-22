package com.example.rest.transformer;

import spark.ResponseTransformer;

import static com.example.rest.mediatype.MediaType.JSON;

public class JsonResponseTransformer implements ResponseTransformer {

    public JsonResponseTransformer() {
        super();
    }

    @Override
    public String render(final Object model) {

        return TransformerFactory.getInstance(JSON).render(model);
    }

}
