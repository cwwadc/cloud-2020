package com.cloud.java8;
import org.junit.Test;
import org.mockito.internal.matchers.ArrayEquals;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 去重复
 */
public class DemoTest6 {

    List<Student> students = Arrays.asList(
            new Student("张三",18,80),
            new Student("李四",19,88),
            new Student("王五",20,85),
            new Student("赵六",17,85),
            new Student("色牛",19,99),
            new Student("色鬼",27,79),
            new Student("色鬼",27,79)
    );

    /**
     * 方式一
     * @param args
     */
    public static void main(String[] args) {
        Person lokesh = new Person(1, "Lokesh", "Gupta");
        Person brian = new Person(2, "Brian", "Clooney");
        Person alex = new Person(3, "Alex", "Kolen");

        //Add some random persons
        Collection<Person> list = Arrays.asList(lokesh,brian,alex,lokesh,brian,lokesh);

        // Get distinct objects by key
        List<Person> distinctElements = list.stream()
                .filter( distinctByKey(p -> p.getId()) )
                .collect( Collectors.toList() );

        // Let's verify distinct elements
        distinctElements.forEach(System.out::println);
    }


    //Utility function
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 方式二
     */
    @Test
    public void test1(){
        List<String> names = new ArrayList<>();//用来临时存储student的name
        List<Student> studentList = students.stream().filter(// 过滤去重
                v -> {
                    boolean flag = !names.contains(v.getName());
                    names.add(v.getName());
                    return flag;
                }
        ).collect(Collectors.toList());
        studentList.forEach(System.out::println);
    }

    /**
     * 方式三
     */
    @Test
    public void test2(){

        List<Student> studentList = students.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(
                                        () -> new TreeSet<>(Comparator.comparing(Student::getName))),
                                ArrayList::new)
                );
        // 增强for循环
        long start = System.currentTimeMillis();
        for (Student student : studentList) {
            System.out.println(student);
        }
        long end = System.currentTimeMillis();
        System.out.println("增强for循环耗时的毫秒数：" + (end - start));

        // lambda表达式循环
        long lambdaStart = System.currentTimeMillis();
        studentList.forEach(System.out::println);
        long lambdaEnd = System.currentTimeMillis();
        System.out.println("lambda表达式循环耗时的毫秒数：" + (lambdaEnd - lambdaStart));
        // for循环
        long simpleStart = System.currentTimeMillis();
        for (int i =0; i <studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
        long simpleEnd = System.currentTimeMillis();
        System.out.println("普通for循环耗时的毫秒数：" + (simpleEnd - simpleStart));
    }
}





