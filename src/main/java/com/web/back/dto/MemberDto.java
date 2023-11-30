package com.web.back.dto;

import com.web.back.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String number;
    private String password;
    private String newPassword;
    private String passwordCheck;
    private String newPasswordCheck;
    private String email;
    private String emailAuthenticationNumber;
    private String name;
    private Integer identity;

    /*public MemberDto setFormat() {
        return new MemberDto();
    }*/

    public MemberDto( String number,  String email, String name, Integer identity) {
        this.number = number;
        this.email = email;
        this.name = name;
        this.identity = identity;
    }

    public static MemberDto setFormat(Member member){
        MemberDto memberDto = new MemberDto(
                member.getNumber(),
                member.getEmail(),
                member.getName(),
                member.getIdentity()
        );

        return memberDto;
    }




}
