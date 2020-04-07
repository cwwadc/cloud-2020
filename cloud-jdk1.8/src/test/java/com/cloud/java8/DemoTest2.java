package com.cloud.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一：lambda 表达式的基础语法：java8引入了一个新的操作符 "->"，该操作符称为箭头操作符或Lambda操作符
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能，即lambda体
 *
 * 语法格式一： 无参数，无返回值
 *        () -> System.out.println("Lambda表达式");
 *
 * 语法格式二： 有一个参数，并且无返回值
 *        (s) -> System.out.println("Lambda表达式" + s);
 *
 * 语法格式三： 若只有一个参数，小括号可以省略不写
 *        s -> System.out.println("Lambda表达式" + s);
 *
 * 语法格式四： 两个参数，有返回值，并且lambda有多条语句
 *       (s1,s2)->{
 *             System.out.println("函数式接口");
 *             return Integer.compare(s1,s2);
 *         };
 *
 *  语法格式五： 若只有一条语句，则大括号和return可以省略
 *   Comparator<Integer> comparator = (s1,s2)-> Integer.compare(s1,s2);
 *
 *  语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出，数据类型，即类型推断
 *      (Integer s1,Integer s2)-> Integer.compare(s1,s2);
 *
 *  二：Lambda 表达式需要"函数式接口"的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用@FunctionalInterface 修饰
 *          可以检查是否是函数式接口
 *
 * */
public class DemoTest2 {

    @Test
    public void test1(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable接口");
            }
        };
        runnable.run();
        System.out.println("---------------------------------------------");

        Runnable r = () -> System.out.println("Lambda表达式");
        r.run();
    }

    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("consumer接口"+ s);
            }
        };
        con.accept("wanwan");

        System.out.println("--------------------------------------------");

        Consumer<String> consumer = (s) -> System.out.println("Lambda表达式" + s);
        consumer.accept("wanwan");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (s1,s2)->{
            System.out.println("函数式接口");
            return Integer.compare(s1,s2);
        };
        int compare = comparator.compare(1, 2);
        System.out.println(compare);
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (s1,s2)-> Integer.compare(s1,s2);
        int compare = comparator.compare(1,2);
        System.out.println(compare);
    }

    @Test
    public void test5(){
        Comparator<Integer> comparator = Integer::compareTo;
        int compare = comparator.compare(1,2);
        System.out.println(compare);
    }

    /**
     * 函数式接口编程 Lambda实现
     */
    @Test
    public void test6(){
        Integer num = opertion(100, x -> x + x);
        System.out.println(num);
        Integer num2 = opertion(100, x -> x * x);
        System.out.println(num2);
    }

    public Integer opertion(int num, MyFun myFun){
        return myFun.addValue(num);
    }
}
