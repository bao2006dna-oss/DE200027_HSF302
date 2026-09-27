package com.example.chap4.service;

import com.example.chap4.pojo.Student;
import java.util.Optional;

public interface StudentService {
    long count();
    Optional<Student> findById(Long id);
}