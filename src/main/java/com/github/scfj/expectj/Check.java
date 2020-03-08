package com.github.scfj.expectj;

public interface Check<T> {
    boolean satisfies(T actual);
}
