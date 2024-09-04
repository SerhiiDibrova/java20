package com.onlyfullstack.bean;

import java.util.Optional;
import java.util.Objects;

public record SmartPhone(Optional<GraphicsCard> graphicsCard) {

    public static SmartPhone of(GraphicsCard graphicsCard) {
        return new SmartPhone(Optional.ofNullable(graphicsCard));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SmartPhone other = (SmartPhone) obj;
        return Objects.equals(graphicsCard, other.graphicsCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphicsCard);
    }

    @Override
    public String toString() {
        return """
                SmartPhone [
                    graphicsCard=%s
                ]
                """.formatted(graphicsCard);
    }
}

record GraphicsCard(String name) {}