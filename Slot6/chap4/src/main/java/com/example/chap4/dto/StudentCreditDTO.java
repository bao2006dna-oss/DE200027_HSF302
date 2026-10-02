package com.example.chap4.dto;

public record StudentCreditDTO(String studentCode, String fullName,
                               Long courseCount, Long totalCredits) {
}