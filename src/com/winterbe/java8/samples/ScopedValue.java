package com.winterbe.java8.samples;

import java.util.HashMap;
import java.util.Map;

public class ScopedValue<T> {
    private static final ThreadLocal<Map<ScopedValue<?>, Object>> values = ThreadLocal.withInitial(HashMap::new);

    private ScopedValue() {}

    public static <T> ScopedValue<T> of(T defaultValue) {
        return new ScopedValue<>();
    }

    public void set(T value) {
        values.get().put(this, value);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        Object value = values.get().get(this);
        if (value == null) {
            throw new NullPointerException("Value is not set");
        }
        return (T) value;
    }

    public static void clear() {
        values.get().clear();
    }
}