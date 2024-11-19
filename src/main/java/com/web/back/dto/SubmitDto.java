package com.web.back.dto;

import com.web.back.model.Submit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SubmitDto {
    private Long id;
    private Integer language;
    private String submit_contents;
    private LocalDateTime submit_time;
    private Integer score;
    private Long problem_id;
    private Long assignment_id;
    private Long member_id;
    private String result;

    public SubmitDto() {

    }

    public SubmitDto(Long id, Integer language, String submit_contents, LocalDateTime submit_time, Integer score, Long member_id, String result) {
        this.id = id;
        this.language = language;
        this.submit_contents = submit_contents;
        this.submit_time = submit_time;
        this.score = score;
        this.member_id = member_id;
        this.result = result;
    }

    public static SubmitDto setFormat(Submit submit) {
        SubmitDto submitDto = new SubmitDto(
                submit.getId(),
                submit.getLanguage(),
                submit.getSubmit_contents(),
                submit.getSubmit_time(),
                submit.getScore(),
                submit.getMember().getId(),
                submit.getResult()
        );
        if (submit.getAssignment() == null) {
            submitDto.setProblem_id(submit.getProblem().getId());
        }
        else {
            submitDto.setAssignment_id(submit.getAssignment().getId());
        }
        return submitDto;
    }
}
