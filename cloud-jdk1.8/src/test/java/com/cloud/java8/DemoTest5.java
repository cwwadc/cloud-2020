package com.cloud.java8;


import com.cloud.java8.thread.Process;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DemoTest5 {


    List<Student> students = Arrays.asList(
            new Student("张三", 18, 80),
            new Student("李四", 19, 88),
            new Student("王五", 20, 85),
            new Student("a六", 17, 85),
            new Student("t七", 17, 85),
            new Student("色牛", 19, 99),
            new Student("色鬼", 27, 79)
    );

    /**
     * Consumer<T> 消费型接口
     * void accept(T t);
     */
    @Test
    public void test1() {
        Consumer consumer = o -> System.out.println("你在干嘛" + o);
        consumer.accept("wanwan");
    }

    /**
     * Function<T, R> 函数式接口
     * R apply(T t);
     */
    @Test
    public void test2() {
        Function<String, String> function = str -> str.substring(2);
        System.out.println(function.apply("abcdefg"));
    }

    /**
     * Supplier<T> 供给型接口
     * T get(); 没有参数
     */
    @Test
    public void test3() {
        Supplier<Student> supplier = () -> new Student("刘亦菲", 18, 1000000);
        System.out.println(supplier.get());
    }

    /**
     * Predicate<T> 断言型接口
     * boolean test(T t);
     * 返回的是布尔类型
     */
    @Test
    public void test4() {
        Predicate<String> predicate = s -> s.length() < 20;
        System.out.println(predicate.test("abasdjhasjdasnasdaskdjasj"));
    }

    /**
     * 线程thread
     */
    @Test
    public void test5(){

        Thread t1 = new MyProcess();
        t1.setName("thread线程");
        t1.setPriority(1); // 设置优先级 1-10
        t1.start();
        Thread t2 = new MyProcess();
        t2.setName("thread线程");
        t2.setPriority(2); // 设置优先级 1-10
        t2.start();
        Runnable r =new Process();
        r.run();
    }

    class MyProcess extends Thread{
        public void run(){
            for (int i =1;i<100; i++ ) {
                System.out.println("第"+ i+"个线程");
            }
        }
    }
}
