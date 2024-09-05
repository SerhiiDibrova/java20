package com.onlyfullstack.bean;

import java.util.Objects;

public record GraphicsCard(String size, GraphicsMemory graphicsMemory) {

    public GraphicsCard {
        Objects.requireNonNull(size);
        Objects.requireNonNull(graphicsMemory);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof GraphicsCard other)
            return Objects.equals(size, other.size) && graphicsMemory == other.graphicsMemory;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, graphicsMemory);
    }

    @Override
    public String toString() {
        return "GraphicsCard [size=" + size + ", graphicsMemory=" + graphicsMemory + "]";
    }
}

enum GraphicsMemory {
    // Add values as needed
}