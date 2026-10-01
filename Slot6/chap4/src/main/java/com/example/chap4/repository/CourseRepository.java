package com.example.chap4.repository;

import com.example.chap4.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    Optional<Course> findByCode(String code);
    boolean existsByCode(String code);
    // TODO 8
    List<Course> findBySemesterOrderByCodeAsc(String semester);
    long countBySemester(String semester);
    // ===== TODO 10 =====
    List<Course> findByStudents_StudentCodeOrderByCodeAsc(String studentCode);
    List<Course> findByStudents_Department_CodeOrderByCodeAsc(String deptCode);         // có thể TRÙNG
    List<Course> findDistinctByStudents_Department_CodeOrderByCodeAsc(String deptCode); // loại trùng
}