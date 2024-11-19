package com.web.back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class SearchResultLessonDto {

    private List<LessonDto> lessonDtos;
    private int totalPage;

    public SearchResultLessonDto(List<LessonDto> lessonDtos, int totalPage) {
        this.lessonDtos = lessonDtos;
        this.totalPage = totalPage;
    }

}