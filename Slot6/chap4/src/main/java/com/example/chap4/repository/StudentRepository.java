package com.example.chap4.repository;

import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {

    // TODO 8
    Optional<Student> findByStudentCode(String studentCode);
    boolean existsByEmail(String email);
    long countByActiveTrue();

    // TODO 9
    List<Student> findByFullNameContainingIgnoreCase(String keyword);
    List<Student> findByEmailEndingWith(String suffix);
    List<Student> findByEmailIsNull();
    // TODO 10
    List<Student> findByGpaBetweenOrderByGpaDesc(double min, double max);
    List<Student> findByGenderAndActiveTrue(Gender gender);
    List<Student> findByDobAfter(LocalDate date);
    // TODO 11
    List<Student> findByDepartmentCode(String deptCode);
    List<Student> findTop3ByOrderByGpaDesc();
}