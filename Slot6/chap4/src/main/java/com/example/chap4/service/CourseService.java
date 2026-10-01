package com.example.chap4.service;

import com.example.chap4.pojo.Course;


import java.util.List;
import java.util.Optional;

public interface CourseService {
    // Khai báo các method ở các TODO tiếp theo
    long count();
    List<Course> findAllOrderByCode();
    Optional<Course> findById(Long id);
}