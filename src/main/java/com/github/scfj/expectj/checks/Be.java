package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.Check;

public class Be<T> implements Check<T> {
    private final T expected;

    public Be(T expectedObject) {
        this.expected = expectedObject;
    }

    public <A extends T> boolean satisfies(A actual) {
        return expected == actual;
    }

    @Override
    public String toString() {
        return "be " + expected;
    }
}
