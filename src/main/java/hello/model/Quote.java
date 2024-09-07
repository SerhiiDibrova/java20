package hello.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Quote(
    @NotBlank
    String type,
    @NotNull
    Value value
) {
    public Quote {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}