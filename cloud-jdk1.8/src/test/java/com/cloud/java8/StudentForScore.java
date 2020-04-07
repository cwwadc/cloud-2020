package com.cloud.java8;

public class StudentForScore implements MyPredicate<Student> {
    @Override
    public boolean test(Student student) {
        return student.getScore()>=80;
    }
}
