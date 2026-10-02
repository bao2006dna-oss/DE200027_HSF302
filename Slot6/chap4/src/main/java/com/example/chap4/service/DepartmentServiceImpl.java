package com.example.chap4.service;

import com.example.chap4.pojo.Department;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import com.example.chap4.repository.DepartmentRepository;
import com.example.chap4.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    @Override
    public long count() {
        return departmentRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public List<Student> findByGpaRange(double min, double max) {
        return List.of();
    }

    @Override
    public List<Student> findActiveByGender(Gender gender) {
        return List.of();
    }

    @Override
    public List<Student> findBornAfter(LocalDate date) {
        return List.of();
    }
    @Override
    public List<Department> findEmptyDepartments() {
        return departmentRepository.findByStudentsIsEmpty();
    }
    @Override
    public List<Student> findActiveStudentsWithMinGpaNative(double minGpa) {
        return studentRepository.findActiveStudentsWithMinGpaNative(minGpa);
    }
    @Transactional
    @Override
    public int updateActiveByDeptCode(String deptCode, boolean active) {
        if (deptCode == null || deptCode.isBlank()) {
            return 0;
        }
        return studentRepository.updateActiveStatusByDepartmentCode(deptCode.trim().toUpperCase(), active);
    }

    @Transactional
    @Override
    public int deleteInactiveStudentsByMinGpa(double minGpa) {
        return studentRepository.deleteInactiveStudentsWithGpaLessThan(minGpa);
    }
    // TODO 22
    @Override
    @Transactional
    public int transferStudentsAndDelete(String fromCode, String toCode) {
        if (fromCode.equals(toCode)) {
            throw new IllegalArgumentException("Khoa nguồn và khoa đích phải khác nhau");
        }
        Department from = departmentRepository.findByCode(fromCode)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + fromCode));
        Department to = departmentRepository.findByCode(toCode)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + toCode));

        int moved = studentRepository.transferStudents(from, to);
        departmentRepository.deleteById(from.getId());
        return moved;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll(Sort.by("id"));
    }

    @Override
    public Collection<?> getDepartmentStatistics() {
        return List.of();
    }
}