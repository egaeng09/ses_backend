package com.web.back.dto;

import com.web.back.model.Lesson;
import com.web.back.model.Member;
import com.web.back.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private Long member_id;
    private Long lesson_id;
    private Integer accept;

    public static StudentDto setFormat(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getMember().getId(),
                student.getLesson().getId(),
                student.getAccept()
        );
        return studentDto;
    }
}
