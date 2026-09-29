package com.example.chap4.service;

import com.example.chap4.dto.DepartmentStat;
import com.example.chap4.dto.StudentSearchCriteria;
import com.example.chap4.dto.StudentSummary;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import com.example.chap4.repository.StudentRepository;
import com.example.chap4.specification.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public long count() {
        return studentRepository.count();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAllOrderByGpaDesc() {
        return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "gpa"));
    }

    @Override
    public Page<Student> findPage(int pageIndex, int size, String sortField) {
        if (pageIndex < 0 || size <= 0) {
            throw new IllegalArgumentException("pageIndex phải >= 0 và size phải > 0");
        }
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(sortField).ascending());
        return studentRepository.findAll(pageable);
    }
    @Override
    public Optional<Student> findByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }

    @Override
    public boolean isEmailExisted(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public long countActive() {
        return studentRepository.countByActiveTrue();
    }
    @Override
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        return studentRepository.findByFullNameContainingIgnoreCase(keyword.trim());
    }

    @Override
    public List<Student> findByEmailDomain(String domain) {
        String suffix = domain.startsWith("@") ? domain : "@" + domain;
        return studentRepository.findByEmailEndingWith(suffix);
    }

    @Override
    public List<Student> findWithoutEmail() {
        return studentRepository.findByEmailIsNull();
    }

    @Override
    public List<Student> findByGpaRange(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min GPA phải <= max GPA");
        }
        return studentRepository.findByGpaBetweenOrderByGpaDesc(min, max);
    }

    @Override
    public List<Student> findActiveByGender(Gender gender) {
        return studentRepository.findByGenderAndActiveTrue(gender);
    }

    @Override
    public List<Student> findBornAfter(LocalDate date) {
        return studentRepository.findByDobAfter(date);
    }
    @Override
    public List<Student> findByDepartmentCode(String deptCode) {
        if (deptCode == null || deptCode.isBlank()) {
            return List.of();
        }
        return studentRepository.findByDepartmentCode(deptCode.trim().toUpperCase());
    }

    @Override
    public List<Student> findTop3HighestGpa() {
        return studentRepository.findTop3ByOrderByGpaDesc();
    }
    @Override
    public List<Student> findByDeptAndMinGpa(String deptCode, double minGpa) {
        if (deptCode == null || deptCode.isBlank()) {
            return List.of();
        }
        return studentRepository.findByDeptCodeAndMinGpa(deptCode.trim().toUpperCase(), minGpa);
    }

    @Override
    public List<Student> searchByNameOrEmailJPQL(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        return studentRepository.searchByNameOrEmailJPQL(keyword.trim());
    }
    @Override
    public List<Student> findAllActiveWithDepartment() {
        return studentRepository.findAllActiveWithDepartmentFetch();
    }

    @Override
    public List<Student> findByDeptCodeWithDepartment(String deptCode) {
        if (deptCode == null || deptCode.isBlank()) {
            return List.of();
        }
        return studentRepository.findByDepartmentCodeFetch(deptCode.trim().toUpperCase());
    }
    @Override
    public List<StudentSummary> getStudentSummariesByDept(String deptCode) {
        if (deptCode == null || deptCode.isBlank()) {
            return List.of();
        }
        return studentRepository.findSummaryByDepartmentCode(deptCode.trim().toUpperCase());
    }

    @Override
    public List<Student> findActiveStudentsWithMinGpaNative(double minGpa) {
        return List.of();
    }

    @Override
    public int updateActiveByDeptCode(String deptCode, boolean active) {
        return 0;
    }

    @Override
    public int deleteInactiveStudentsByMinGpa(double minGpa) {
        return 0;
    }

    @Override
    public List<Student> searchDynamic(StudentSearchCriteria criteria) {
        return studentRepository.findAll(StudentSpecification.filter(criteria));
    }
    @Override
    public Page<Student> searchDynamicPageable(StudentSearchCriteria criteria, int pageIndex, int size, String sortField, String sortDirection) {
        if (pageIndex < 0 || size <= 0) {
            throw new IllegalArgumentException("pageIndex phải >= 0 và size phải > 0");
        }
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(direction, sortField));

        return studentRepository.findAll(StudentSpecification.filter(criteria), pageable);
    }


    @Override
    public List<Student> findStudentsWithComplexConditionCustom(String deptCode, double minGpa, boolean activeOnly) {
        return studentRepository.findStudentsWithComplexConditionCustom(deptCode, minGpa, activeOnly);
    }

    @Override
    public List<DepartmentStat> getDepartmentStatistics() {
        return studentRepository.getDepartmentStatistics();
    }
    @Override
    @Transactional
    public int deactivateLowGpa(double threshold) {
        return studentRepository.deactivateLowGpa(threshold);
    }
    @Override
    public Long countByDepartment(String deptCode) {
        return studentRepository.countByDepartmentCode(deptCode);
    }


}