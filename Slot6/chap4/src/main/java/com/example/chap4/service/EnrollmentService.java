package com.example.chap4.service;

import com.example.chap4.pojo.Course;
import com.example.chap4.pojo.Student;

import java.util.List;

public interface EnrollmentService {
    // Khai báo các method ở các TODO tiếp theo
    // ===== TODO 7 =====
    List<Course> getCoursesOfStudent(String studentCode);
    List<Student> getStudentsOfCourse(String courseCode);
    // ===== TODO 9 =====
    List<Student> findStudentsInCourse(String courseCode);
    long countStudentsInCourse(String courseCode);
    List<Student> findActiveStudentsInCourse(String courseCode);
}