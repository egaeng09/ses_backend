package com.web.back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class SearchResultManageDto {

    private List<MemberDto> memberDtos;
    private int totalPage;

    public SearchResultManageDto(List<MemberDto> memberDtos, int totalPage) {
        this.memberDtos = memberDtos;
        this.totalPage = totalPage;
    }

}