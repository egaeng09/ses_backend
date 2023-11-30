package com.web.back.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private MemberDto memberDto;
}