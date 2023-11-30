package com.web.back.dto;

import com.web.back.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime upload_time;
    private String writer;

    public static CommentDto setFormat(Comment comment) {
        CommentDto commentDto = new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getUpload_time(),
                comment.getWriter().getName()
        );
        return commentDto;
    }
}
