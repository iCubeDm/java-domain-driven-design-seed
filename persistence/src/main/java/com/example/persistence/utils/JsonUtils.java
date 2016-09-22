package com.example.persistence.utils;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author: dmitry.yakubovsky
 * date:   06/04/16
 */
public class JsonUtils {

    private static final Gson gson = new Gson();

    public static String toJson(final Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(final String json, final Class<T> clazz) {

        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(final InputStream in, final Class<T> clazz) throws FileNotFoundException {
        return gson.fromJson(new InputStreamReader(in), clazz);
    }
}
