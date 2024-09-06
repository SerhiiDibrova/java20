package hello.model;

import java.util.Objects;

public record Value(Long id, String quote) {

    public Value {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(quote, "quote must not be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Value value = (Value) obj;
        return Objects.equals(id, value.id) && Objects.equals(quote, value.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quote);
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}