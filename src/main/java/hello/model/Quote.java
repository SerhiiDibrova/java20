package hello.model;

import java.util.Objects;

public class Quote {
    private String type;
    private Value value;

    public Quote() {}

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + (type != null ? type : "null") + '\'' +
                ", value=" + (value != null ? value.toString() : "null") +
                '}';
    }

    public String getType() {
        return Objects.requireNonNullElse(type, "");
    }

    public void setType(String type) {
        this.type = Objects.requireNonNullElseGet(type, () -> "");
    }

    public Value getValue() {
        return Objects.requireNonNullElse(value, new Value());
    }

    public void setValue(Value value) {
        this.value = Objects.requireNonNullElseGet(value, Value::new);
    }
}