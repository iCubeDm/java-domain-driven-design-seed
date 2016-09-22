package com.example.rest.transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTransformer implements Transformer {

    private final Gson gson;

    JsonTransformer() {
        super();
        final GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
    }

    @Override
    public <T> T render(final String entity, final Class<T> classOfT) {

        return gson.fromJson(entity, classOfT);
    }

    @Override
    public String render(final Object entity) {

        return gson.toJson(entity);
    }
}
