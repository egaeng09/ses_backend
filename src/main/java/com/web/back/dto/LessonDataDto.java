package com.web.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 강의실 정보 + 해당 강의 과제 목록 정보
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDataDto {
    private LessonDto lesson;
    private List<AssignmentDto> assignment;
}
