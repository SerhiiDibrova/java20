package com.onlyfullstack.bean;

import java.util.Objects;

public class GraphicsCard {

    private String size;
    private GraphicsMemory graphicsMemory;

    public GraphicsCard(String size, GraphicsMemory graphicsMemory) {
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Size cannot be empty or null");
        }
        this.size = size.trim();
        if (graphicsMemory == null) {
            throw new NullPointerException("Graphics memory cannot be null");
        }
        this.graphicsMemory = graphicsMemory;
    }

    public GraphicsCard() {}

    public GraphicsMemory getGraphicsMemory() {
        return graphicsMemory;
    }

    public void setGraphicsMemory(GraphicsMemory graphicsMemory) {
        if (graphicsMemory == null) {
            throw new NullPointerException("Graphics memory cannot be null");
        }
        this.graphicsMemory = graphicsMemory;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Size cannot be empty or null");
        }
        this.size = size.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicsCard that = (GraphicsCard) o;
        return Objects.equals(size, that.size) && Objects.equals(graphicsMemory, that.graphicsMemory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, graphicsMemory);
    }

    @Override
    public String toString() {
        return "GraphicsCard{" +
                "size='" + size + '\'' +
                ", graphicsMemory=" + graphicsMemory +
                '}';
    }
}

class GraphicsMemory {

    private long capacity;
    private String type;

    public GraphicsMemory(long capacity, String type) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.capacity = capacity;
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be empty or null");
        }
        this.type = type.trim();
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be empty or null");
        }
        this.type = type.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicsMemory that = (GraphicsMemory) o;
        return capacity == that.capacity && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, type);
    }

    @Override
    public String toString() {
        return "GraphicsMemory{" +
                "capacity=" + capacity +
                ", type='" + type + '\'' +
                '}';
    }
}