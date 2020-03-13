package com.github.scfj.expectj;

import com.github.scfj.expectj.checks.*;
import com.github.scfj.expectj.checks.collections.Include;

import java.util.Collection;

public class Dsl {
    public static Expectation<UnsafeRunnable> expect(UnsafeRunnable runnable) {
        return new Expectation<UnsafeRunnable>(runnable);
    }

    public static <E> Expectation<E> expect(E value) {
        return new Expectation<E>(value);
    }

    public static <T> Be<T> be(T expectedObject) {
        return new Be<T>(expectedObject);
    }

    public static <T> Equal<T> beEqual(T expectedObject) {
        return new Equal<T>(expectedObject);
    }

    public static <T extends Comparable<T>> GreaterThan<T> beGreaterThan(T thresholdValue) {
        return new GreaterThan<T>(thresholdValue);
    }

    public static <T extends Comparable<T>> LessThan<T> beLessThan(T thresholdValue) {
        return new LessThan<T>(thresholdValue);
    }

    public static Check<UnsafeRunnable> failWith(Class<? extends Throwable> errorType) {
        return new FailWith<UnsafeRunnable>(errorType);
    }

    public static <V> Check<Iterable<V>> include(V... values) {
        return new Include<V>(values);
    }

    public static <V> Check<Iterable<V>> include(Collection<V> values) {
        return new Include<V>(values);
    }
}
