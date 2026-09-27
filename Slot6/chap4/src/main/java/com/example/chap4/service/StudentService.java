package com.example.chap4.service;

import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    long count();
    Optional<Student> findById(Long id);
    // TODO 7
    List<Student> findAllOrderByGpaDesc();
    Page<Student> findPage(int pageIndex, int size, String sortField);
    Optional<Student> findByStudentCode(String studentCode);
    boolean isEmailExisted(String email);
    long countActive();
    // TODO 9
    List<Student> searchByName(String keyword);
    List<Student> findByEmailDomain(String domain);
    List<Student> findWithoutEmail();
    // TODO 10
    List<Student> findByGpaRange(double min, double max);
    List<Student> findActiveByGender(Gender gender);
    List<Student> findBornAfter(LocalDate date);
    // TODO 11
    List<Student> findByDepartmentCode(String deptCode);
    List<Student> findTop3HighestGpa();
    // TODO 12
    List<Student> findByDeptAndMinGpa(String deptCode, double minGpa);
    List<Student> searchByNameOrEmailJPQL(String keyword);
}