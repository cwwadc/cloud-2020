package com.cloud.java8;

public class StudentCompare implements MyPredicate<Student> {
    @Override
    public boolean test(Student student) {
        return student.getAge()>=20 && student.getScore() >=80;
    }
}
