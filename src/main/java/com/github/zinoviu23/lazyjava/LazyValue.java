package com.github.zinoviu23.lazyjava;

public class LazyValue<T> {
    private T value;

    private boolean computed = false;

    private Computation<T> computation;

    public LazyValue(Computation<T> computation) {
        this.computation = computation;
    }

    public T getValue() {
        if (!computed) {
            computed = true;
            value = computation.compute();
            computation = null;
        }

        return value;
    }
}
