package com.cloud;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream流 02
 */
public class DemoMain04 {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        Student s1 = new Student("张三", 21);
        Student s2 = new Student("李四", 19);
        Student s3 = new Student("王五", 18);
        Student s4 = new Student("程六", 17);
        Student s5 = new Student("赵七", 20);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

//        list.forEach( item ->{
//            System.out.println("姓名：" + item.getName() + "，年龄：" + item.getAge());
//        });
        // 查询小于20岁年龄最大的学生
//        Student student = list.stream().filter((s) -> s.getAge() < 20).max(Comparator.comparing(s -> s.getAge())).get();
//        System.out.println(student);

        // 查询小于20岁年龄最小的学生
//        Student student1 = list.stream().filter((s) -> s.getAge() < 20).min(Comparator.comparing(s -> s.getAge())).get();
//        System.out.println(student1);

        // 查询小于20岁年龄最小的学生
        List<Student> studentList = list.stream().filter((s) -> s.getAge() < 20).collect(Collectors.toList());
        studentList.forEach(System.out::println);
    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    static
//    class Student {
//        private String name;
//        private int age;

//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Student(String name, int age){
//            this.age=age;
//            this.name=name;
//        }
//
//    }
}