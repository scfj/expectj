package com.github.scfj.expectj;

public class Expectation<T> {
    private final T actual;

    public Expectation(T value) {
        this.actual = value;
    }

    public void to(Check<T> check) {
        if (!check.satisfies(actual)) {
            raise("to", check);
        }
    }

    public void notTo(Check<T> check) {
        if (check.satisfies(actual)) {
            raise("not to", check);
        }
    }

    private void raise(String message, Check<T> check) {
        throw new AssertionError(
                String.format("Expected %s %s %s.", actual, message, check)
        );
    }
}
