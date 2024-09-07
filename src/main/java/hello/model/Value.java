package hello.model;

public record Value(Long id, String quote) {
    public Value {
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}