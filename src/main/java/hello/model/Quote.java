package hello.model;

public record Quote(String type, Value value) {
    public Quote {
        Objects.requireNonNull(type);
        Objects.requireNonNull(value);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}