package com.web.back.dto;

import lombok.Data;

@Data
public class EmailDto {
    String email;
    String verificationCode;
    String enteredVerificationCode;
    Boolean validate;
}
