package com.cloud;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Lambda 表达式
 */
public class DemoMain01 {

    public static void main(String[] args) {
        // 定义字符串数组
        String[] strArr = { "abc", "cd", "abce", "a" };

        //匿名内部类
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        for (String s : strArr) {
            System.out.println("输出结果是："+s);
        }

        // Lambda表达式
        Arrays.sort(strArr, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        for (String s : strArr) {
            System.out.println("输出结果是："+s);
        }

        // 第三种方式
        Arrays.sort(strArr, Comparator.comparingInt(String::length));

        for (String s : strArr) {
            System.out.println("输出结果是："+s);
        }

    }
}
