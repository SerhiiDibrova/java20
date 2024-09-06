package hello.declaration;

import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
public interface CustomPredicate<T> extends Predicate<T> {

    @Override
    default boolean test(T t) {
        Objects.requireNonNull(t, "Input parameter cannot be null");
        return testInternal(t);
    }

    boolean testInternal(T t);

}