package com.sparta.jpaschedule.dto;

import com.sparta.jpaschedule.entity.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {

    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long schedule_id;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.username = comments.getUsername();
        this.contents = comments.getContents();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.schedule_id = comments.getSchedule().getId();
    }
}
