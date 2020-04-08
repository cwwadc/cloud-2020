package com.cloud.java8;

@FunctionalInterface
public interface MyFunction<T> {
    Object getValue(T t);
}
