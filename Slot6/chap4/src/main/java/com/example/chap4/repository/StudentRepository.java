package com.example.chap4.repository;

import com.example.chap4.dto.StudentSummary;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    // TODO 12: JPQL Queries
    @Query("SELECT s FROM Student s WHERE s.department.code = :deptCode AND s.gpa >= :minGpa")
    List<Student> findByDeptCodeAndMinGpa(@Param("deptCode") String deptCode, @Param("minGpa") double minGpa);

    @Query("SELECT s FROM Student s WHERE LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByNameOrEmailJPQL(@Param("keyword") String keyword);
    // TODO 13: JOIN FETCH Department cùng với Student
    @Query("SELECT s FROM Student s JOIN FETCH s.department WHERE s.active = true")
    List<Student> findAllActiveWithDepartmentFetch();

    @Query("SELECT s FROM Student s JOIN FETCH s.department WHERE s.department.code = :deptCode")
    List<Student> findByDepartmentCodeFetch(@Param("deptCode") String deptCode);
    @Query("SELECT s.studentCode AS studentCode, s.fullName AS fullName, s.gpa AS gpa, s.department.name AS departmentName " +
            "FROM Student s WHERE s.department.code = :deptCode")
    List<StudentSummary> findSummaryByDepartmentCode(@Param("deptCode") String deptCode);
}