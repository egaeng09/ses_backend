package com.web.back.controller.board;


import com.web.back.dto.*;
import com.web.back.service.BoardService;
import com.web.back.utils.ErrorResponseManager;
//import com.web.back.utils.TokenCookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private ErrorResponseManager errorResponseManager;

    @Autowired
    private BoardService boardService;

    // 게시판 화면, 게시글 목록 요청
    @GetMapping("/board/{boardIdx}")
    public ResponseEntity<?> openBoard(@PathVariable("boardIdx") Long boardIdx) throws Exception {
        try {
            List<PostDto> post = boardService.getPostList(boardIdx);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 작성 요청 (아마 글 내용 넘어오면 등록하는 기능)
    @PostMapping("/board/{boardIdx}/write")
    public ResponseEntity<?> writePost(@PathVariable("boardIdx") Long boardIdx, @RequestBody PostDto postDto) throws Exception {
        try {
            return new ResponseEntity<>(boardService.registerPost(boardIdx, postDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 상세 정보 조회
    @GetMapping("/board/{boardIdx}/{postIdx}")
    public ResponseEntity<?> openPostDetail(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx) throws Exception {
        try {
            PostDto post = boardService.getPostDetail(postIdx);
            List<CommentDto> comments= boardService.getCommentList(postIdx);
            PostDataDto sendData = new PostDataDto(post, comments);
            return new ResponseEntity<>(sendData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 수정 요청
    @PutMapping("/board/{boardIdx}/edit/{postIdx}")
    public ResponseEntity<?> updatePost(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx, @RequestBody PostDto postDto) throws Exception {
        try {
            PostDto post = boardService.editPost(postIdx, postDto);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 삭제 요청
    @DeleteMapping("/board/{boardIdx}/delete/{postIdx}")
    public ResponseEntity<?> deletePost(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx) throws Exception {
        try {
            boardService.deletePost(postIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 작성
    @PostMapping("/board/{boardIdx}/{postIdx}/comment")
    public ResponseEntity<?> registerComment(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx, @RequestBody CommentDto commentDto) throws Exception {
        try {
            CommentDto comment = boardService.registerComment(postIdx, commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 수정 요청
    @PutMapping("/board/{boardIdx}/{postIdx}/edit/{commentIdx}")
    public ResponseEntity<?> editComment(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx, @PathVariable("commentIdx") Long commentIdx, @RequestBody CommentDto commentDto) throws Exception {
        try {
            CommentDto comment = boardService.editComment(commentIdx, commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 삭제
    @DeleteMapping("/board/{boardIdx}/{postIdx}/delete/{commentIdx}")
    public ResponseEntity<?> deleteComment(@PathVariable("boardIdx") Long boardIdx, @PathVariable("postIdx") Long postIdx, @PathVariable("commentIdx") Long commentIdx) throws Exception {
        try {
            boardService.deleteComment(commentIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 검색
    @GetMapping("/board/{boardIdx}/search")
    public ResponseEntity<?> searchPost(@PathVariable("boardIdx") Long boardIdx,
                                        @RequestParam(value="keyword") String keyword,
                                        @RequestParam(value="page") int page) throws Exception {
        try {
            SearchResultBoardDto posts = boardService.searchPost(boardIdx, keyword, page);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

}
