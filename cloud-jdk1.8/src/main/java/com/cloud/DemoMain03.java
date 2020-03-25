package com.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream流
 */
public class DemoMain03 {

    static void main(String[] args) {
        // 定义字符串数组
        String[] strArr = { "abc", "cd", "abce", "a" };

        //匿名内部类
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        List<String> list = new ArrayList<>();
        for (String s : strArr) {
            list.add(s);
        }
//        list.forEach(item->{
//            System.out.println("输出结果是："+item);
//        });

        long count = list.stream().filter((item) -> item.length() > 2).count();
        System.out.println("输出结果是：" + count);
        //找出大于2的集合并返回
        List<String> list1 = list.stream().filter((s) -> s.length() > 2).collect(Collectors.toList());
        list1.forEach(System.out :: println);
        mapStreams(list);

    }

    static void mapStreams(List<String> list) {
        //转换为大写
        List<String> list2 = list.stream().map((s) -> s.toUpperCase()).collect(Collectors.toList());
        list2.forEach(System.out::println);
    }
}
