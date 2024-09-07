package hello.model;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class Quote {
    private @NotNull String type;
    private @NotNull Value value;

    public Quote(@NotNull String type, @NotNull Value value) {
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

    public String getType() {
        return type;
    }

    public void setType(@NotNull String type) {
        this.type = Objects.requireNonNull(type);
    }

    public Value getValue() {
        return value;
    }

    public void setValue(@NotNull Value value) {
        this.value = Objects.requireNonNull(value);
    }
}