package com.web.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDataDto {
    private Long id;
    private String number;
    private String name;
    private String code;
    private int score;
}
