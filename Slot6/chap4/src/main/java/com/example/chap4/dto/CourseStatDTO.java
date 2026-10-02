package com.example.chap4.dto;

public record CourseStatDTO(String code, String name, Integer capacity,
                            Long enrolled, Double avgGpa) {

    public long remaining() {                 // số chỗ còn trống
        return capacity - enrolled;
    }
}