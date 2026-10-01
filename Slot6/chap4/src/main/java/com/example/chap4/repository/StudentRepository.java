package com.example.chap4.repository;
import com.example.chap4.pojo.Department;
import com.example.chap4.dto.DepartmentStat;
import com.example.chap4.dto.StudentSummary;
import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student>,
        StudentRepositoryCustom {

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

    // TODO 12
    @Query("SELECT s FROM Student s WHERE s.department.code = :deptCode AND s.gpa >= :minGpa")
    List<Student> findByDeptCodeAndMinGpa(@Param("deptCode") String deptCode, @Param("minGpa") double minGpa);

    @Query("SELECT s FROM Student s WHERE LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByNameOrEmailJPQL(@Param("keyword") String keyword);

    // TODO 13
    @Query("SELECT s FROM Student s JOIN FETCH s.department WHERE s.active = true")
    List<Student> findAllActiveWithDepartmentFetch();

    @Query("SELECT s FROM Student s JOIN FETCH s.department WHERE s.department.code = :deptCode")
    List<Student> findByDepartmentCodeFetch(@Param("deptCode") String deptCode);

    // TODO 14
    @Query("SELECT s.studentCode AS studentCode, s.fullName AS fullName, s.gpa AS gpa, s.department.name AS departmentName " +
            "FROM Student s WHERE s.department.code = :deptCode")
    List<StudentSummary> findSummaryByDepartmentCode(@Param("deptCode") String deptCode);

    // TODO 15
    @Query(value = "SELECT * FROM students s WHERE s.gpa >= :minGpa AND s.is_active = 1", nativeQuery = true)
    List<Student> findActiveStudentsWithMinGpaNative(@Param("minGpa") double minGpa);

    // TODO 16
    @Modifying
    @Query("UPDATE Student s SET s.active = :active WHERE s.department.code = :deptCode")
    int updateActiveStatusByDepartmentCode(@Param("deptCode") String deptCode, @Param("active") boolean active);

    @Modifying
    @Query("DELETE FROM Student s WHERE s.active = false AND s.gpa < :minGpa")
    int deleteInactiveStudentsWithGpaLessThan(@Param("minGpa") double minGpa);
    // TODO 20: Aggregation & Group By Query
    @Query("SELECT s.department.code AS departmentCode, " +
            "s.department.name AS departmentName, " +
            "COUNT(s) AS studentCount, " +
            "AVG(s.gpa) AS avgGpa " +
            "FROM Student s " +
            "GROUP BY s.department.code, s.department.name")
    List<DepartmentStat> getDepartmentStatistics();

    // TODO 21: @Modifying UPDATE
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Student s SET s.active = false WHERE s.gpa < :threshold AND s.active = true")
    int deactivateLowGpa(@Param("threshold") double threshold);
    // TODO 22: Transfer students from one department to another
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Student s SET s.department = :to WHERE s.department = :from")
    int transferStudents(@Param("from") Department from, @Param("to") Department to);


    Long countByDepartmentCode(String deptCode);
    // TODO 23: Derived delete
    long deleteByActiveFalse();
    // ===== Exercise 2 — TODO 9 =====
    List<Student> findByCourses_CodeOrderByFullNameAsc(String courseCode);
    long countByCourses_Code(String courseCode);
    List<Student> findByCourses_CodeAndActiveTrueOrderByFullNameAsc(String courseCode);
    // ===== Exercise 2 — TODO 11 =====
    List<Student> findByCoursesIsEmptyOrderByFullNameAsc();
    boolean existsByStudentCodeAndCourses_Code(String studentCode, String courseCode);
}