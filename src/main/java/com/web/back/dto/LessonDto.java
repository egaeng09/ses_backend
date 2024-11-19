package com.web.back.dto;

import com.web.back.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LessonDto {
    private Long id;

    private int number;

    private String name;

    private String year;

    private String semester;

    private String owner;

    private Long owner_id;

    public LessonDto() {

    }

    public LessonDto(Long id, String name, String year, String semester, String owner, Long owner_id) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.owner = owner;
        this.owner_id = owner_id;
    }

    public static LessonDto setFormat(Lesson lesson) {
        LessonDto lessonDto = new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getYear(),
                lesson.getSemester(),
                lesson.getMember().getName(),
                lesson.getMember().getId()
        );

        return lessonDto;
    }

    public static LessonDto setFormat(Lesson lesson, int number) {
        LessonDto lessonDto = new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getYear(),
                lesson.getSemester(),
                lesson.getMember().getName(),
                lesson.getMember().getId()
        );

        lessonDto.setNumber(number);

        return lessonDto;
    }
}
