package com.web.back.dto;

import lombok.Data;

import java.util.List;

// 게시글 + 댓글 리스트 (게시글 상세 조회 용도)
@Data
public class PostDataDto{
    PostDto post;
    List<CommentDto> comments;

    public PostDataDto(PostDto newPost, List<CommentDto> newComments) {
        post = newPost;
        comments = newComments;
    }
}