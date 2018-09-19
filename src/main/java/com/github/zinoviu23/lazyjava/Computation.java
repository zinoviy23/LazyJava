package com.github.zinoviu23.lazyjava;

@FunctionalInterface
public interface Computation<T> {
    T compute();
}
