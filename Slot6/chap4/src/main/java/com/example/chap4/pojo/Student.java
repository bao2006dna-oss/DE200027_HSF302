package com.example.chap4.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", length = 20, nullable = false, unique = true)
    private String studentCode;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(length = 100, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private LocalDate dob;

    private Double gpa;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + studentCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", gpa=" + gpa +
                ", active=" + active +
                '}';
    }
    // Bổ sung các import này nếu trong class chưa có


    // Bên trong class Student, thêm trường courses:
    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // Thêm 2 hàm helper đồng bộ 2 chiều:
    public void enroll(Course c) {
        courses.add(c);
        c.getStudents().add(this);
    }

    public void unenroll(Course c) {
        courses.remove(c);
        c.getStudents().remove(this);
    }
    // Helper method kiểm tra active không bị null
    public boolean isActive() {
        return Boolean.TRUE.equals(this.active);
    }
}