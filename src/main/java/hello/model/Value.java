package hello.model;

public record Value(Long id, String quote) {
    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }
}