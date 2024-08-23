package com.sparta.jpaschedule.controller;

import com.sparta.jpaschedule.dto.CommentsRequestDto;
import com.sparta.jpaschedule.dto.CommentsResponseDto;
import com.sparta.jpaschedule.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    //일정 저장
    //등록할 일정의 id을 @PathVariable로, 댓글의 내용을 body로 받아오기
    @PostMapping("/schedule/{id}")
    public CommentsResponseDto createComments(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
        return commentsService.createComments(id, requestDto);
    }

    //단건 조회
    @GetMapping("/{id}")
    public CommentsResponseDto getComments(@PathVariable Long id) {
        return commentsService.getComments(id);
    }

    //다건 조회
    @GetMapping
    public List<CommentsResponseDto> getCommentsList() {
        return commentsService.getCommentsList();
    }

    //수정
    @PutMapping("/{id}")
    public CommentsResponseDto updateComments(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
        return commentsService.updateComments(id, requestDto);
    }

    //삭제
    @DeleteMapping("/{id}")
    public Long deleteComments(@PathVariable Long id) {
        return commentsService.deleteComments(id);
    }

}
