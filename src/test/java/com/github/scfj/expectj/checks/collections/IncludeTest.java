package com.github.scfj.expectj.checks.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.scfj.expectj.Dsl.expect;
import static com.github.scfj.expectj.Dsl.include;
import static java.util.Arrays.asList;

public class IncludeTest {
    @Test
    public void should_include_subset() {
        List<Integer> list = asList(1, 2, 3);
        expect(list).to(include(1));
        expect(list).to(include(2, 3));
    }

    @Test
    public void should_include_itself() {
        expect(asList(1, 2, 3)).to(include(1, 2, 3));
    }

    @Test(expected = AssertionError.class)
    public void should_not_include_superset() {
        expect(asList("a", "b")).to(include("a", "b", "c"));
    }

    @Test
    public void different_collections() {
        List<Character> java = asList('j', 'a', 'v', 'a');
        Set<Character> jav = new HashSet<Character>(java);
        expect(java).to(include(jav));
    }
}
