package com.github.scfj.expectj;

public interface Check<T> {
    <A extends T> boolean satisfies(A actual);
}
