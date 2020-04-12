package com.cloud.java8;

/**
 * 手写断言接口
 * @param <T>
 */
public interface MyPredicate<T> {

    public boolean test(T t);
}
