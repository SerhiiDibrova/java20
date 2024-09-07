package hello.model;

public record Greeting(long id, String content) {
    public Greeting {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be non-negative");
        }
        if (content == null || content.isEmpty() || !content.matches(".*\\w+.*")) {
            throw new IllegalArgumentException("Content must not be empty or null and should contain at least one word character");
        }
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}