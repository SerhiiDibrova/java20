package com.onlyfullstack.bean;

public class GraphicsCard {

    private String size;
    private GraphicsMemory graphicsMemory;

    public GraphicsCard(String size, GraphicsMemory graphicsMemory) {
        this.size = size;
        this.graphicsMemory = graphicsMemory;
    }

    public GraphicsCard() {}

    public GraphicsMemory getGraphicsMemory() {
        return graphicsMemory;
    }

    public void setGraphicsMemory(GraphicsMemory graphicsMemory) {
        this.graphicsMemory = graphicsMemory;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "GraphicsCard [size=" + size + "]";
    }
}

class GraphicsMemory {

    private String type;
    private int capacity;

    public GraphicsMemory(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}