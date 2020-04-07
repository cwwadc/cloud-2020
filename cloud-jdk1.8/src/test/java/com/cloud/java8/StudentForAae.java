package com.cloud.java8;

public class StudentForAae implements MyPredicate<Student>{

    @Override
    public boolean test(Student student) {
        return student.getAge() >= 20;
    }
}
