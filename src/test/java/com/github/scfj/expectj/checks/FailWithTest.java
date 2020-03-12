package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.UnsafeRunnable;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.github.scfj.expectj.Dsl.expect;
import static com.github.scfj.expectj.Dsl.failWith;

/**
 * In Java 8+ lambda expressions can be used:
 * expect(() -> ((String) null).length()).to(failWith(NullPointerException.class));
 */
public class FailWithTest {
    @Test(expected = AssertionError.class)
    public void different_exceptions_should_fail() {
        expect(new UnsafeRunnable() {
            @Override
            public void run() throws Throwable {
                throw new IOException();
            }
        }).to(failWith(NullPointerException.class));
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
    @Test
    public void exceptions_of_same_type_should_pass() {
        expect(new UnsafeRunnable() {
            @Override
            public void run() {
                ((String) null).length();
            }
        }).to(failWith(NullPointerException.class));
    }

    @Test(expected = AssertionError.class)
    public void no_exception_should_fail() {
        expect(new UnsafeRunnable() {
            @Override
            public void run() {
            }
        }).to(failWith(NullPointerException.class));
    }

    @Test
    public void exception_of_subtype_should_pass() {
        expect(new UnsafeRunnable() {
            @Override
            public void run() throws Throwable {
                throw new FileNotFoundException();
            }
        }).to(failWith(IOException.class));
    }
}
