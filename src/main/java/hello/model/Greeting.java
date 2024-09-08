package hello.model;

public record Greeting(long id, String content) {
    public Greeting {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be non-negative");
        }
        Objects.requireNonNull(content, "Content cannot be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Greeting other = (Greeting) obj;
        return id == other.id && content.equals(other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}