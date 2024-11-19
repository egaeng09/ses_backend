package com.web.back.dto;

import com.web.back.model.Assignment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AssignmentDto {
    private Long id;
    private int number;
    private String title;
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private Integer language;
    private Long lessonId;

    private Integer submitStudents;
    private Integer totalStudents;

    private String solve;

    private String submit_contents;

    public AssignmentDto() {

    }

    public AssignmentDto(Long id, String title, String description, LocalDateTime start_time, LocalDateTime end_time, Integer language, Long lessonId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.language = language;
        this.lessonId = lessonId;
    }

    public static AssignmentDto setFormat(Assignment assignment) {
        AssignmentDto assignmentDto = new AssignmentDto(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getStart_time(),
                assignment.getEnd_time(),
                assignment.getLanguage(),
                assignment.getLesson().getId()
        );
        return assignmentDto;
    }

    public static AssignmentDto setFormat(Assignment assignment, int number) {
        AssignmentDto assignmentDto = new AssignmentDto(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getStart_time(),
                assignment.getEnd_time(),
                assignment.getLanguage(),
                assignment.getLesson().getId()
        );
        assignmentDto.setNumber(number);
        return assignmentDto;
    }

    public static AssignmentDto setFormat(Assignment assignment, int number, String solve) {
        AssignmentDto assignmentDto = new AssignmentDto(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getStart_time(),
                assignment.getEnd_time(),
                assignment.getLanguage(),
                assignment.getLesson().getId()
        );
        assignmentDto.setNumber(number);
        assignmentDto.setSolve(solve);
        return assignmentDto;
    }

    public static AssignmentDto setFormat(Assignment assignment, int number, int submit, int total) {
        AssignmentDto assignmentDto = new AssignmentDto(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getStart_time(),
                assignment.getEnd_time(),
                assignment.getLanguage(),
                assignment.getLesson().getId()
        );
        assignmentDto.setNumber(number);
        assignmentDto.setSubmitStudents(submit);
        assignmentDto.setTotalStudents(total);
        return assignmentDto;
    }
}
