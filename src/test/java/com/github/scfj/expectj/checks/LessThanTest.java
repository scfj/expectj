package com.github.scfj.expectj.checks;

import org.junit.Test;

import static com.github.scfj.expectj.Dsl.*;

public class LessThanTest {
    @Test
    public void should_pass_with_4_and_5() {
        expect(4).to(beLessThan(5));
    }

    @Test(expected = AssertionError.class)
    public void object_should_not_be_less_than_itself() {
        String object = "Object";
        expect(object).to(beLessThan(object));
    }

    @Test(expected = AssertionError.class)
    public void object_should_not_be_less_than_greater_value() {
        expect(4).to(beLessThan(3));
    }
}
