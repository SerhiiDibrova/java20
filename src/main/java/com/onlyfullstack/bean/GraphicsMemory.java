package com.onlyfullstack.bean;

import java.util.Objects;

/**
 * Represents graphics memory information.
 */
public record GraphicsMemory(String dedicatedMemory, String memoryType) {

    /**
     * Constructs a new GraphicsMemory instance.
     *
     * @param dedicatedMemory the dedicated memory
     * @param memoryType       the memory type
     * @throws NullPointerException if either dedicatedMemory or memoryType is null
     */
    public GraphicsMemory {
        Objects.requireNonNull(dedicatedMemory, "dedicatedMemory must not be null");
        Objects.requireNonNull(memoryType, "memoryType must not be null");

        // Validate input parameters
        if (dedicatedMemory.isBlank() || memoryType.isBlank()) {
            throw new IllegalArgumentException("Both dedicatedMemory and memoryType must be non-blank strings");
        }
    }

    /**
     * Returns a string representation of this GraphicsMemory instance.
     *
     * @return a string representation of this GraphicsMemory instance
     */
    @Override
    public String toString() {
        return "GraphicsMemory[dedicatedMemory=" + dedicatedMemory + ", memoryType=" + memoryType + "]";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        GraphicsMemory other = (GraphicsMemory) obj;
        return Objects.equals(dedicatedMemory, other.dedicatedMemory) && Objects.equals(memoryType, other.memoryType);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(dedicatedMemory, memoryType);
    }
}