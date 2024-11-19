package com.web.back.dto;

import com.web.back.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private int number;
    private String title;
    private String content;
    private LocalDateTime upload_time;
    private Integer views;
    private Long writer_id;
    private String writer;

    public PostDto() {

    }

    public PostDto(Long id, String title, String content, LocalDateTime upload_time, Integer views, Long writer_id, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.upload_time = upload_time;
        this.views = views;
        this.writer_id = writer_id;
        this.writer = writer;
    }

    public static PostDto setFormat(Post post) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUpload_time(),
                post.getViews(),
                post.getWriter().getId(),
                post.getWriter().getName()
        );
        return postDto;
    }

    public static PostDto setFormat(Post post, int number) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUpload_time(),
                post.getViews(),
                post.getWriter().getId(),
                post.getWriter().getName()
        );
        postDto.setNumber(number);
        return postDto;
    }
}
