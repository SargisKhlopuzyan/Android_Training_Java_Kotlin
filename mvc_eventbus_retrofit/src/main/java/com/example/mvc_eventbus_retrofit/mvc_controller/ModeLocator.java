package com.example.mvc_eventbus_retrofit.mvc_controller;

import java.util.HashMap;
import java.util.Map;

public class ModeLocator {

    public enum Tag {
        ITEM_PHOTOS,
    }

    private static Map<Tag, Object> showcase = new HashMap<>();

    private ModeLocator() {
    }

    public static void register(Tag tag, Object object) {
        showcase.put(tag, object);
    }

    public static Object get(Tag tag) {
        return showcase.get(tag);
    }
}
