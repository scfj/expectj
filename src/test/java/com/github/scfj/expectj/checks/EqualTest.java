package com.github.scfj.expectj.checks;

import org.junit.Test;

import static com.github.scfj.expectj.Dsl.beEqual;
import static com.github.scfj.expectj.Dsl.expect;

public class EqualTest {
    @Test
    public void equal_objects_should_pass() {
        expect(3).to(beEqual(3));
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    @Test
    public void different_but_equal_objects_should_pass() {
        String original = "Object";
        String copy = new String(original);
        expect(copy).to(beEqual(original));
        expect(original).to(beEqual(copy));
    }

    @Test(expected = AssertionError.class)
    public void not_equal_objects_should_fail() {
        expect("A").to(beEqual("B"));
    }
}
