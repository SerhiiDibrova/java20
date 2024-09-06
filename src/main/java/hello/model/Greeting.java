package hello.model;

public record Greeting(long id, String content) {
    public Greeting {
        Objects.requireNonNull(content, "content must not be null");
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}