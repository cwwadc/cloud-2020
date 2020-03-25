package com.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * forEach
 */
public class DemoMain02 {

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
        list.forEach(item->{
            System.out.println("输出结果是："+item);
        });

    }
}
