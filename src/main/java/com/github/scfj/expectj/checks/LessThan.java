package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.Check;

public class LessThan<T extends Comparable<T>> implements Check<T> {
    private final T threshold;

    public LessThan(T thresholdValue) {
        this.threshold = thresholdValue;
    }

    public <A extends T> boolean satisfies(A actual) {
        return threshold.compareTo(actual) > 0;
    }

    @Override
    public String toString() {
        return "be less than " + threshold;
    }
}
