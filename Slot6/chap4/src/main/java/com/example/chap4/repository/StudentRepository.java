package com.example.chap4.repository;

import com.example.chap4.pojo.Student;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {
    // Sẽ bổ sung method ở các TODO tiếp theo

    // TODO 8
    Optional<Student> findByStudentCode(String studentCode);
    boolean existsByEmail(String email);
    long countByActiveTrue();


}
