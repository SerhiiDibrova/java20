package hello.declaration;

public sealed interface CustomPredicate<T> permits Object {
    boolean test(T t);
}