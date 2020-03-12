package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.Check;

public class GreaterThan<T extends Comparable<T>> implements Check<T> {
    private final T threshold;

    public GreaterThan(T thresholdValue) {
        this.threshold = thresholdValue;
    }

    public boolean satisfies(T actual) {
        return threshold.compareTo(actual) < 0;
    }

    @Override
    public String toString() {
        return "be greater than " + threshold;
    }
}
