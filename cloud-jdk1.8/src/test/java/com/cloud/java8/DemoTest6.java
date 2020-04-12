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
            new Student("色鬼",27,79),
            new Student("色鬼",27,79)
    );

    List<Student> students2 = Arrays.asList(
            new Student("张三",18,80),
            new Student("李四",19,88),
            new Student("张俪",20,85),
            new Student("刘亦菲",17,85),
            new Student("老王",27,79)
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
     * 方式二 stream API去重复
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
     * 方式三 stream API去重复
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

    /**
     * 方式四 for循环contains去重复，要精确需重写equals方法
     */
    @Test
    public void test4(){
        List<Student> studentList = new ArrayList<>();
        for(int i =0; i< students.size(); i++){
            Student student = students.get(i);
            if (!studentList.contains(student)){
                studentList.add(student);
            }
        }
        for (int i=0; i<studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
    }

    /**
     * 迭代器循环contains去重复，要精确需重写equals方法
     * 方式五 迭代器未进化版
     *
     */
    @Test
    public void test5(){
        List<Student> studentList = iterList(students);
        studentList.forEach(System.out::println);
    }

    public List<Student> iterList(List<Student> list){
        List<Student> studentList = new ArrayList<>();
        Iterator it = list.iterator();
        while(it.hasNext()){
            Student next = (Student) it.next();
            if (!studentList.contains(next)){
                studentList.add(next);
            }
        }
        return studentList;
    }

    /**
     * 方式六
     * 利用map重复元素会覆盖的原理去重复
     *
     */
    @Test
    public void test6(){
        removeDuplicate(students).forEach(System.out::println);
    }

    /***
     * 利用map重复元素会覆盖的原理去重复
     * 去除List<Student>列表中的重复对象 ~!
     * @param list
     * @return
     */
    public static List<Student> removeDuplicate(List<Student> list) {
        List<Student> newList = new ArrayList<>();
        Map<String,Student> map = new HashMap<>();
        Iterator<Student> it = list.iterator();
        while (it.hasNext()){
            Student element = it.next();
            map.put(element.getName(),element);
        }
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            Student student = map.get(iterator.next());
            newList.add(student);
        }
        return newList;
    }

    /**
     * 方式七： LinkedHashSet去重复
     */
    @Test
    public void test7(){
        List<Student> list = new ArrayList<>(new LinkedHashSet<>(students));
        list.forEach(System.out::println);
    }

}





