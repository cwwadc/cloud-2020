package com.cloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream流 04
 *
 * 通常编写并行代码很难而且容易出错,
 *
 * 但使用 Stream API 无需编写一行多线程的代码
 *
 * 就可以很方便地写出高性能的并发程序 
 *
 * a.调用Stream的parallel()方法
 *
 * b.调用Collection的parallelStream()方法
 *
 * c.parallel() 与 sequential() 可在并行流与顺序流之间切换
 *
 */
public class DemoMain06 {

    public static void main(String[] args) {

        List<Long> list = new ArrayList<>();
        for(long i = 0 ; i <= 100; i++){
            list.add(i);
        }
//        list.forEach(System.out::println);
        // 计算
        // reduce：参1，和的初始值
        Long sum = list.stream().parallel().reduce(0L, (r, l) -> r + l);
        System.out.println(sum);
    }
}
