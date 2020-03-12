package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.Check;

public class Equal<T> implements Check<T> {
    private final T expected;

    public Equal(T expectedObject) {
        this.expected = expectedObject;
    }

    public boolean satisfies(T actual) {
        return expected.equals(actual);
    }

    @Override
    public String toString() {
        return "be equal to " + expected;
    }
}
