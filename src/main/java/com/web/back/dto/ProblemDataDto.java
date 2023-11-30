package com.web.back.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProblemDataDto {
    ProblemDto problem;
    List<SubmitDto> submits = new ArrayList<>();
}
