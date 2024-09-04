package com.onlyfullstack.bean;

import java.util.Objects;

public record GraphicsMemory(String dedicatedMemory, String memoryType) {

    public GraphicsMemory {
        Objects.requireNonNull(dedicatedMemory, "dedicatedMemory must not be null");
        Objects.requireNonNull(memoryType, "memoryType must not be null");
        if (dedicatedMemory.isEmpty() || memoryType.isEmpty()) {
            throw new IllegalArgumentException("Both dedicatedMemory and memoryType must not be empty");
        }
    }

    public String getDedicatedMemory() {
        return dedicatedMemory;
    }

    public String getMemoryType() {
        return memoryType;
    }
}