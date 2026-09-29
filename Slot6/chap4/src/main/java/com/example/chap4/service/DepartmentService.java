package com.example.chap4.service;


import com.example.chap4.pojo.Department;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface DepartmentService {
    // Khai báo các method ở đây
    long count();
    boolean existsById(Long id);
    // TODO 10
    List<Student> findByGpaRange(double min, double max);
    List<Student> findActiveByGender(Gender gender);

    List<Student> findBornAfter(LocalDate date);

    // TODO 11
    List<Department> findEmptyDepartments();

    List<Student> findActiveStudentsWithMinGpaNative(double minGpa);

    @Transactional
    int updateActiveByDeptCode(String deptCode, boolean active);

    @Transactional
    int deleteInactiveStudentsByMinGpa(double minGpa);
    // TODO 22
    int transferStudentsAndDelete(String fromCode, String toCode);
    List<Department> findAll();

    Collection<?> getDepartmentStatistics();
}
