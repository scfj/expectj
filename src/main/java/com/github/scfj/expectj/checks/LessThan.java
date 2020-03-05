package com.github.scfj.expectj;

public class LessThan<T extends Comparable<T>> implements Check<T> {
    private final T threshold;

    public LessThan(T thresholdValue) {
        this.threshold = thresholdValue;
    }

    public boolean satisfies(T actual) {
        return threshold.compareTo(actual) > 0;
    }

    @Override
    public String toString() {
        return "be less than " + threshold;
    }
}
