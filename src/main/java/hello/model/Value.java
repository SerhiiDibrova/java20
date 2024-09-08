package hello.model;

public record Value(Long id, String quote) {
    public Value {
        if (id == null || quote == null) {
            throw new NullPointerException("Neither id nor quote can be null");
        }
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