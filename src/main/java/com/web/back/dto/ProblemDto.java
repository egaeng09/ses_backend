package com.web.back.dto;

import com.web.back.model.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProblemDto {
    private Long id;
    private int number;
    private String title;
    private String descripsion;
    private String submit_contents;
    private Integer language;
    private LocalDateTime upload_time;
    private Long uploader_id;
    private String uploader;
    String solve;

    public ProblemDto() {

    }

    public ProblemDto(Long id, String title, String descripsion,  Integer language, LocalDateTime upload_time, Long uploader_id, String uploader) {
        this.id = id;
        this.title = title;
        this.descripsion = descripsion;
        this.language = language;
        this.upload_time = upload_time;
        this.uploader_id = uploader_id;
        this.uploader = uploader;
    }



    public static ProblemDto setFormat(Problem problem) {
        ProblemDto problemDto = new ProblemDto(
                problem.getId(),
                problem.getTitle(),
                problem.getDescripsion(),

                problem.getLanguage(),
                problem.getUpload_time(),
                problem.getMember().getId(),
                problem.getMember().getName()
        );

        return problemDto;
    }

    public static ProblemDto setFormat(Problem problem, int number) {
        ProblemDto problemDto = new ProblemDto(
                problem.getId(),
                problem.getTitle(),
                problem.getDescripsion(),
                problem.getLanguage(),
                problem.getUpload_time(),
                problem.getMember().getId(),
                problem.getMember().getName()
        );
        problemDto.setNumber(number);
        return problemDto;
    }
    public static ProblemDto setFormat(Problem problem, int number, String solve) {
        ProblemDto problemDto = new ProblemDto(
                problem.getId(),
                problem.getTitle(),
                problem.getDescripsion(),
                problem.getLanguage(),
                problem.getUpload_time(),
                problem.getMember().getId(),
                problem.getMember().getName()
        );
        problemDto.setNumber(number);
        problemDto.setSolve(solve);
        return problemDto;
    }
    public static ProblemDto setFormat(Problem problem, String submit_contents) {
        ProblemDto problemDto = new ProblemDto(
                problem.getId(),
                problem.getTitle(),
                problem.getDescripsion(),
                problem.getLanguage(),
                problem.getUpload_time(),
                problem.getMember().getId(),
                problem.getMember().getName()
        );
        problemDto.setSubmit_contents(submit_contents);
        return problemDto;
    }
}
