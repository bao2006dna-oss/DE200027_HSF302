package com.example.chap4.dto;

public interface StudentSummary {
    String getStudentCode();
    String getFullName();
    Double getGpa();
    String getDepartmentName(); // Nested property: student.department.name
}