package com.example.chap4.service;


import com.example.chap4.pojo.Department;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentService {
    // Khai báo các method ở đây
    long count();
    boolean existsById(Long id);
    // TODO 10
    List<Student> findByGpaRange(double min, double max);
    List<Student> findActiveByGender(Gender gender);

    List<Student> findBornAfter(LocalDate date);

    // TODO 11
    List<Department> findEmptyDepartments();

    List<Student> findActiveStudentsWithMinGpaNative(double minGpa);
}
