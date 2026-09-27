package com.example.chap4.repository;

import com.example.chap4.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByCode(String code);
    // TODO 11
    List<Department> findByStudentsIsEmpty();
}