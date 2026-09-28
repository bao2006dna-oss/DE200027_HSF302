package com.example.chap4.repository;

import com.example.chap4.pojo.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findStudentsWithComplexConditionCustom(String deptCode, double minGpa, boolean activeOnly);
}