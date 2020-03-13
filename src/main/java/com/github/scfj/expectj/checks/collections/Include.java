package com.github.scfj.expectj.checks.collections;

import com.github.scfj.expectj.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Include<T> implements Check<Iterable<T>> {
    private final Collection<T> shouldBeIncluded;
    private final List<T> missingItems = new ArrayList<T>();

    public Include(Collection<T> values) {
        this.shouldBeIncluded = values;
    }

    public Include(T... values) {
        this.shouldBeIncluded = Arrays.asList(values);
    }

    @Override
    public <A extends Iterable<T>> boolean satisfies(A actual) {
        List<?> items = asList(actual);
        for (T value : shouldBeIncluded) {
            if (!items.contains(value)) {
                missingItems.add(value);
            }
        }
        return missingItems.isEmpty();
    }

    @Override
    public String toString() {
        return String.format(
                "contain %s, but %s were not found",
                shouldBeIncluded,
                missingItems
        );
    }

    private static <E> List<E> asList(Iterable<E> iterable) {
        List<E> list = new ArrayList<E>();
        for (E item : iterable) {
            list.add(item);
        }
        return list;
    }
}
