package com.example.chap4.repository;

import com.example.chap4.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chap4.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Sẽ bổ sung method ở các TODO tiếp theo
}
