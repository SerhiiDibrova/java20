package hello.model;

import java.util.Objects;

public record Greeting(long id, String content) {
    public Greeting {
        Objects.requireNonNull(content, "content must not be null");
        if (id < 0) {
            throw new IllegalArgumentException("id must not be negative");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Greeting other = (Greeting) obj;
        return id == other.id && Objects.equals(content, other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @Override
    public String toString() {
        return "Greeting[id=" + id + ", content='" + content + "']";
    }
}