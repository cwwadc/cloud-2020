package com.cloud.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Optional
 * <p>
 * ifPresent方法
 * 不为空时进行操作，内部自动判断null处理
 */
public class DemoTest3 {

    List<Student> students = Arrays.asList(
            new Student("张三", 18, 80),
            new Student("李四", 19, 88),
            new Student("王五", 20, 85),
            new Student("赵六", 17, 85),
            new Student("色牛", 19, 99),
            new Student("色鬼", 27, 79)
    );

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 20, 6999.00),
            new Employee("李四", 27, 12999.66),
            new Employee("王五", 26, 8999.00),
            new Employee("赵六", 22, 9999.00),
            new Employee("色牛", 22, 7999.00),
            new Employee("色鬼", 25, 8999.00)
    );

    /**
     * ifPresent 方式一
     */
    @Test
    public void test1() {
        Optional.ofNullable(students).ifPresent(list ->
                        list.stream()
                        .filter((s) ->
                        s.getAge() > 18 && s.getScore() > 80)
                        .forEach(System.out::println));

    }

    /**
     * ifPresent 方式二
     */
    @Test
    public void test2() {
        Optional.ofNullable(students).ifPresent(list ->
                list.stream().filter((s) -> s.getAge() > 18)
                        .filter((s) -> s.getScore() > 80)
                        .forEach(System.out::println));

    }

    /**
     * 需求：查出年龄相同的list集合
     *  哪个集合在里面返回的就是里面那个集合
     */
    @Test
    public void test3() {
        List<Employee> employeeList = students.stream()
                .map(
                        student -> emps.stream()
                                .filter(employee -> student.getAge() == (employee.getAge()))
                                .findAny()
                                .orElse(null)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        students.stream()
                .map(
                        s -> emps.stream()
                                .filter(e -> s.getAge() == e.getAge())
                                .findAny()
                                .orElse(null)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------------------------------------------------------------");
        emps.stream()
                .map(
                        e -> students.stream()
                        .filter(s -> s.getAge() == e.getAge())
                        .findAny()
                        .orElse(null)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList()).forEach(System.out::println);

    }


}
