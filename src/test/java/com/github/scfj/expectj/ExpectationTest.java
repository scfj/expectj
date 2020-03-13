package com.github.scfj.expectj;

import org.junit.Test;

public class ExpectationTest {
    @Test
    public void should_check() {
        Any<Long> beAny = new Any<Long>();
        new Expectation<Long>(3L).to(beAny);
        assert beAny.invokedWith(3L) : "Expected to invoke check with 3";
    }

    @Test(expected = AssertionError.class)
    public void should_throw_assertion_error() {
        None<Long> beNone = new None<Long>();
        new Expectation<Long>(3L).to(beNone);
    }

    static class Any<T> implements Check<T> {
        boolean invoked;
        T value;

        @Override
        public <A extends T> boolean satisfies(A actual) {
            invoked = true;
            value = actual;
            return true;
        }

        public boolean invokedWith(T value) {
            return invoked && this.value.equals(value);
        }
    }

    static class None<T> implements Check<T> {
        @Override
        public <A extends T> boolean satisfies(A actual) {
            return false;
        }
    }
}
