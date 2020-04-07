package com.cloud.java8;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoTest1 {


    List<Student> students = Arrays.asList(
            new Student("张三",18,80),
            new Student("李四",19,88),
            new Student("王五",20,85),
            new Student("赵六",17,85),
            new Student("色牛",19,99),
            new Student("色鬼",27,79)
    );
    /**
     * 方式一： 增强for循环遍历
     */
    @Test
    public void test1(){
        List<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() > 20){
                studentList.add(student);
            }
        }
        for (Student student : studentList) {
            System.out.println(student.getName() + " " + student.getAge()+" " + student.getScore());
        }
    }

    /**
     * 方式二： 策略设计模式
     */
    @Test
    public void test2(){
        List<Student> students = filterStudent(this.students, new StudentForAae());
        students.forEach(System.out::println);
        System.out.println("-----------------------------");
        List<Student> students1 = filterStudent(this.students, new StudentForScore());
        students1.forEach(System.out::println);
        System.out.println("-----------------------------");
        List<Student> students2 = filterStudent(this.students, new StudentCompare());
        students2.forEach(System.out::println);
    }

    public List<Student> filterStudent(List<Student> studentList, MyPredicate<Student> myPredicate){
        List<Student> stus= new ArrayList<>();
        for (Student student : studentList) {
            if (myPredicate.test(student)){
                stus.add(student);
            }
        }
        return stus;
        
    }

    /**
     * 方式三： 匿名内部类
     */
    @Test
    public void test3(){
        List<Student> list = filterStudent(this.students, new MyPredicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAge() >= 20 && student.getScore()>=80;
            }
        });
        for (Student student : list) {
            System.out.println(student.getAge());
        }
    }


    /**
     * 方式三： lambda 表达式
     */
    @Test
    public void test4(){
        List<Student> list = filterStudent(students, (s) -> s.getAge()>= 20 && s.getScore() >=80);
        list.forEach(System.out::println);
    }

    /**
     * 方式四：stream API
     */
    @Test
    public void test5(){
        students.stream()
                .filter((s) -> s.getAge()>=20 && s.getScore()>=80)
                .forEach(System.out::println);
    }

}
