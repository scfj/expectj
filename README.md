# ExpectJ

## Problem

```java
import org.junit.Test;
import org.junit.Assert;

class AssertTest {
    @Test
    public void assertEqualsTest() {
        Assert.assertEquals("expected here?", "or here?");
        Assert.assertEquals("Is it actual?", "Is this error message?", "Oh...");
    }
}
```

## Solution -- ExpectJ

Small library to make assertions more readable. It's compatible with all test frameworks.

```java
import org.junit.Test;

import com.github.scfj.expectj.Dsl.*;

class AssertTest {
    /**
     * This example will fail and print
     * java.lang.AssertionError: Expected ACTUAL VALUE to be equal to EXPECTED VALUE
     *     *Stacktrace*
     */
    @Test
    public void assertEqualsTest() {
        expect("ACTUAL VALUE").to(equal("EXPECTED VALUE"));
    }
}
```

## How to use it?

Add dependency to your project

```xml
<dependency>
    <groupId>com.github.scfj</groupId>
    <artifactId>expectj</artifactId>
    <version>1.0</version>
    <scope>test</scope>
</dependency>
```

Works with Java 6+!

## More examples

Example 1.
```java
import org.junit.Test;
import com.github.scfj.expectj.Dsl.*;

class ExampleTest {
    int result = 3;

    @Test
    public void assertionsExamples() {
        expect(result).toBe(equal(3));
        // or equivalent expect(result).to(equal(3));
        expect("output").toBe(greaterThan("aaa"));

        expect("output").to(be(new String("output"))); // fails
        expect("output").toBe(equal(new String("output"))); // passes
    }
    
    /**
     * Test without static methods.
     * Demonstrates what happens inside.
     */
    @Test
    public void withoutDsl() {
        new Actual<String>("Author").notTo(new Equal<String>());
        // Equal implements Check, that means you can easily write your check
    }
}
```

Example 2. - Exceptions showcase.
```java
import org.junit.Test;
import com.github.scfj.expectj.UnsafeRunnable;
import com.github.scfj.expectj.Dsl.*;
import java.io.FileNotFoundException;import java.io.IOException;

class ExampleTest {
    @Test
    public void exceptionExample() {
        File file = new File("/no such file.txt");
        expect(new UnsafeRunnable() {
            public void run() throws Throwable {
                file.content(); // read content of file that does not exist
            }
        }).to(failWith(IOException.class));
    }

    @Test
    public void java8Example() {
        File file = new File("/no such file.txt");
        expect(()-> file.content()).to(failWith(IOException.class));
        // or
        epxect(file::content).to(failWith(FileNotFoundException.class));
    }
}
```

All the checks available out of the box can be found in -
[Dsl.java](src/main/java/com/github/scfj/expectj/Dsl.java).

More test examples can be found in tests -
[src/test/checks/*](src/test/java/com/github/scfj/expectj/checks).

## Extending DSL

You can easily extend this DSL by implementing interface Check<T>.

Example 1.
```java
class Authenticated implements Check<User> {
    public static Authenticated beAuthenticated() { new Authenticated(); }

    @Override
    public boolean satisfies(User user) { /* decide whether user is authenticated */ }
}

public class UserTest {
    @Test
    public void test() {
        expect(someUser).to(beAuthenticated());
    }
}
```

Example 2.
```java
class Even implements Check<Number> {
    public static Even beEven() {
        return new Even();
    }

    @Override
    public boolean satisfies(Number x) {
        return x.intValue() % 2 == 0;
    }
}

public class PowerTest {
    @Test
    public void test() {
        expect(powerOfTwo(10)).to(beEven());
    }
}
```            

Rewrite this example even shorter in Java 8:
```java
public class PowerTest {                
    Check<Number> beEven = (x) -> x.intValue() % 2 == 0;
                        
    @Test                                                   
    public void test() {                                    
        expect(powerOfTwo(10)).to(beEven);                
    }                                                       
}                                                           
```
