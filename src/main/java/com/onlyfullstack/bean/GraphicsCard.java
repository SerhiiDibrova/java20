package com.onlyfullstack.bean;

import java.util.Objects;
import java.util.Optional;

public record GraphicsCard(String size, GraphicsMemory graphicsMemory) {

    public static GraphicsCard of(String size, GraphicsMemory graphicsMemory) {
        Objects.requireNonNull(graphicsMemory, "Graphics memory cannot be null");
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty or null");
        }
        return new GraphicsCard(size, graphicsMemory);
    }

    public static Optional<GraphicsCard> ofOptional(String size, GraphicsMemory graphicsMemory) {
        if (size == null || size.isEmpty() || graphicsMemory == null) {
            return Optional.empty();
        }
        return Optional.of(new GraphicsCard(size, graphicsMemory));
    }

    @Override
    public String toString() {
        return "GraphicsCard [size=" + size + ", graphicsMemory=" + graphicsMemory + "]";
    }
}

class GraphicsMemory {

    private final String type;
    private final int capacity;

    public GraphicsMemory(String type, int capacity) {
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "GraphicsMemory [type=" + type + ", capacity=" + capacity + "]";
    }
}