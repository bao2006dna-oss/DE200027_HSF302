package com.example.chap4.dto;

import com.example.chap4.pojo.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSearchCriteria {
    private String keyword;     // Tìm trong fullName hoặc email
    private String deptCode;    // Mã phòng ban
    private Gender gender;      // Giới tính
    private Double minGpa;      // GPA tối thiểu
    private Double maxGpa;      // GPA tối đa
    private Boolean active;     // Trạng thái active
}