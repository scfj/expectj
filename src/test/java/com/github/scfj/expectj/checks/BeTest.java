package com.github.scfj.expectj.checks;

import org.junit.Test;

import static com.github.scfj.expectj.Dsl.be;
import static com.github.scfj.expectj.Dsl.expect;

public class BeTest {
    @Test
    public void should_pass_with_the_same_object() {
        String obj = "Object";
        expect(obj).to(be(obj));
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    @Test(expected = AssertionError.class)
    public void should_fail_on_different_but_equal_objects() {
        String obj1 = "Object";
        String obj2 = new String(obj1);
        expect(obj1).to(be(obj2));
    }
}
