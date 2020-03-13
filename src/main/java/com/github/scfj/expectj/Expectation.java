package com.github.scfj.expectj;

public class Expectation<T> {
    private final T actual;

    public Expectation(T value) {
        this.actual = value;
    }

    public void to(Check<? super T> check) {
        if (!check.satisfies(actual)) {
            raise("to", check);
        }
    }

    public void notTo(Check<? super T> check) {
        if (check.satisfies(actual)) {
            raise("not to", check);
        }
    }

    private void raise(String message, Check<?> check) {
        throw new AssertionError(
                String.format("Expected %s %s %s.", actual, message, check)
        );
    }
}
