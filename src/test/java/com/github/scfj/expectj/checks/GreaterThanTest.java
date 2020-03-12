package com.github.scfj.expectj.checks;

import org.junit.Test;

import static com.github.scfj.expectj.Dsl.expect;
import static com.github.scfj.expectj.Dsl.beGreaterThan;

public class GreaterThanTest {
    @Test
    public void should_pass_with_4_and_5() {
        expect(5).to(beGreaterThan(4));
    }

    @Test(expected = AssertionError.class)
    public void object_should_not_be_greater_than_itself() {
        String object = "Object";
        expect(object).to(beGreaterThan(object));
    }

    @Test(expected = AssertionError.class)
    public void object_should_not_be_greater_than_smaller_value() {
        expect(4).to(beGreaterThan(5));
    }
}
