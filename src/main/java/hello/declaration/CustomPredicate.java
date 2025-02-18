package hello.declaration;

@FunctionalInterface
public interface CustomPredicate<T> {
    boolean test(T t);
}