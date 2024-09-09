package hello.model;

public record Value(Long id, String quote) {
    public Value {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(quote, "quote must not be null");
    }

    @Override
    public String toString() {
        return """
                Value{
                    id=%s,
                    quote='%s'
                }
                """.formatted(id, quote);
    }
}