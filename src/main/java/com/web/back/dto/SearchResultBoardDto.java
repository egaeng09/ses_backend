package com.web.back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class SearchResultBoardDto {

    private List<PostDto> postDtos;
    private int totalPage;

    public SearchResultBoardDto(List<PostDto> postDtos, int totalPage) {
        this.postDtos = postDtos;
        this.totalPage = totalPage;
    }

}