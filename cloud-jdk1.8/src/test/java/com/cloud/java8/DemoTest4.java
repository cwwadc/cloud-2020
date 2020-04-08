package com.cloud.java8;


import org.junit.Test;

import javax.print.DocFlavor;
import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DemoTest4 {


    List<Student> students = Arrays.asList(
            new Student("张三",18,80),
            new Student("李四",19,88),
            new Student("王五",20,85),
            new Student("a六",17,85),
            new Student("t七",17,85),
            new Student("色牛",19,99),
            new Student("色鬼",27,79)
    );
    /**
     * 排序
     */
    @Test
    public void test1(){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAge() == o2.getAge()){
                    return Double.compare(o1.getScore(),o2.getScore());
                }else {
                    return Integer.compare(o1.getAge(),o2.getAge());
                }
            }
        });
        students.forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------");
        Collections.sort(students, (o1, o2) -> {
            if (o1.getAge() > o2.getAge()){
                return 1; // 从抵到高排序
            }else if (o1.getAge() < o2.getAge()){
                return -1;
            }else {
                if (o1.getScore() > o2.getScore()){
                    return 1;
                }else if (o1.getScore() < o2.getScore()){
                    return -1;
                }else{
                    return o1.getName().compareTo(o2.getName());
                }

            }
        });

        students.forEach(System.out::println);


    }

    @Test
    public void test2(){
        String strHand =(String) strHandler("\t\t\t  八神科技第一公司", o -> {
            String o1 = (String) o;
            return o1.trim().substring(0, 4);
        });
        System.out.println(strHand);

        int num =(int) strHandler(100, o ->(int) o * 100);
        System.out.println(num);
    }

    /**
     * 需求：用于处理字符串
     */
    public Object strHandler(Object str, MyFunction myFun){

        return myFun.getValue(str);
    }

    /**
     * 方法引用 小试牛刀
     */
    @Test
    public void test3(){
        Supplier<Student> supplier = () -> new Student();
        Supplier<Student> supplier1 = Student::new;
        System.out.println(supplier1.get());

        Function<Integer,String[]> function = String[]::new;
        String[] str = function.apply(20);
        System.out.println(str.length);
        Function<Integer,Integer[]> function1 =Integer[]::new;
        Integer[] str1 = function1.apply(10);
        System.out.println(str1.length);

    }


}
