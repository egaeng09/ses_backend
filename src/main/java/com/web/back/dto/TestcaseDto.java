package com.web.back.dto;

import com.web.back.model.Submit;
import com.web.back.model.Testcase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TestcaseDto {

    private Long id;

    private String input;

    private String output;

    private Long problem_id;

    private Long assignment_id;

    public TestcaseDto() {

    }

    public TestcaseDto(Long id, String input, String output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public static TestcaseDto setFormat(Testcase testcase) {
        TestcaseDto testcaseDto = new TestcaseDto(
                testcase.getId(),
                testcase.getInput(),
                testcase.getOutput()
        );
        if (testcase.getAssignment() == null) {
            testcaseDto.setProblem_id(testcase.getProblem().getId());
        }
        else {
            testcaseDto.setAssignment_id(testcase.getAssignment().getId());
        }
        return testcaseDto;
    }
}
