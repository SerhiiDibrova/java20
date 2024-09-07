package com.winterbe.java8.samples;

import java.util.HashMap;
import java.util.Map;

public record ScopedValue<T>(T defaultValue) {
    private static final ThreadLocal<Map<ScopedValue<?>, Object>> values = ThreadLocal.withInitial(HashMap::new);

    public void set(T value) {
        var map = values.get();
        map.put(this, value);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        var map = values.get();
        return (T) map.getOrDefault(this, defaultValue);
    }

    public static void clear() {
        var map = values.get();
        map.clear();
    }
}