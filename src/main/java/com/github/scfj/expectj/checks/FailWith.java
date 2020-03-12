package com.github.scfj.expectj.checks;

import com.github.scfj.expectj.Check;
import com.github.scfj.expectj.UnsafeRunnable;

public class FailWith<T extends UnsafeRunnable> implements Check<T> {
    private final Class<? extends Throwable> errorType;

    public FailWith(Class<? extends Throwable> errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "throw " + errorType.getSimpleName();
    }

    @Override
    public boolean satisfies(T actual) {
        try {
            actual.run();
        } catch (Throwable throwable) {
            if (errorType.isInstance(throwable)) {
                return true;
            }
        }
        return false;
    }
}
