package hello.model;

public class Quote {
    private String type;
    private Value value;

    public Quote() {}

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

public class Value {
    private final String id;
    private final String quote;

    public Value(String id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }
}