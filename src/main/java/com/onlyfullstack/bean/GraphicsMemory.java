package com.onlyfullstack.bean;

import java.util.Objects;

public record GraphicsMemory(String dedicatedMemory, String memoryType) {

    public GraphicsMemory {
        Objects.requireNonNull(dedicatedMemory);
        Objects.requireNonNull(memoryType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        GraphicsMemory other = (GraphicsMemory) obj;
        return dedicatedMemory.equals(other.dedicatedMemory) && memoryType.equals(other.memoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dedicatedMemory, memoryType);
    }

    @Override
    public String toString() {
        return "GraphicsMemory[dedicatedMemory=%s, memoryType=%s]".formatted(dedicatedMemory, memoryType);
    }
}