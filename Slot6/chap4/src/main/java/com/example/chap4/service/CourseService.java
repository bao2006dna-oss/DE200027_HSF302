package com.example.chap4.service;

import com.example.chap4.pojo.Course;


import java.util.List;
import java.util.Optional;

public interface CourseService {
    // Khai báo các method ở các TODO tiếp theo
    long count();
    List<Course> findAllOrderByCode();
    Optional<Course> findById(Long id);
    // TODO 7 & 8: Derived query tìm môn học theo mã code
    Optional<Course> findByCode(String code);
    // TODO 8

    List<Course> findBySemester(String semester);
    long countBySemester(String semester);
    // ===== TODO 10 =====
    List<Course> findCoursesOfStudent(String studentCode);
    List<Course> findCoursesOfDepartment(String deptCode, boolean distinct);
    // ===== TODO 11 =====
    List<Course> findCoursesWithoutStudents();
}