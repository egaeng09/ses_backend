package com.web.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor

public class SearchResultDto {
    private List<ProblemDto> problemDtos;
    private List<PostDto> postDtos;

    private List<LessonDto> lessonDtos;
    private int totalPage;

    public SearchResultDto(List<ProblemDto> problemDtos, int totalPage) {
        this.problemDtos = problemDtos;
        this.totalPage = totalPage;
    }

}