package hello.model;

public record Value(Long id, String quote) {
    public Value {
        Objects.requireNonNull(id);
        Objects.requireNonNull(quote);
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