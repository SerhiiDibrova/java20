package hello.model;

public record Quote(String type, Value value) {
    public Quote {
        this.type = Objects.requireNonNull(type);
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String toString() {
        return """
                Quote{
                    type='%s',
                    value=%s
                }
                """.formatted(type, value);
    }

    public static Quote of(String type, Value value) {
        return new Quote(type, value);
    }
}