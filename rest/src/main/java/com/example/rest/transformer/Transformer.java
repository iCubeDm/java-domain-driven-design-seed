package com.example.rest.transformer;

public interface Transformer {

    <T> T render(final String entity, final Class<T> classOfT);

    String render(final Object entity);

}
