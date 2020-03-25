package com.cloud;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流 03
 */
public class DemoMain05 {

    public static void main(String[] args) {

        // 找到开头为a的字符串并串联起来
        String collect = Stream.of("a", "abc", "efg", "abcd").filter(s -> s.startsWith("a")).collect(Collectors.joining());
        System.out.println(collect);

        List<String> collect1 = Stream.of("a", "abc", "efg", "abcd").filter(s -> s.startsWith("a")).collect(Collectors.toList());

        collect1.forEach(System.out::println);

        Set<Integer> collect2 = Stream.of("a", "abc", "efg", "abcd").filter(s -> s.startsWith("a"))
                .map(s -> s.length()).collect(Collectors.toSet());
        collect2.forEach(System.out::print);

    }
}
