package com.sparta.jpaschedule.dto;

import com.sparta.jpaschedule.entity.Register;
import com.sparta.jpaschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private Long user_id;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int commentscount;
    private List<Long> users_id = new ArrayList<>();
    private List<String> users_name = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.user_id = schedule.getUser_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.commentscount = schedule.getCommentsList().size();
    }

    public ScheduleResponseDto(Schedule schedule, List<Register> list) {
        this.id = schedule.getId();
        this.user_id = schedule.getUser_id();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.commentscount = schedule.getCommentsList().size();

        for(int i=0; i<list.size(); i++) {
            this.users_id.add(list.get(i).getUser().getId());
            this.users_name.add(list.get(i).getUser().getUsername());
            this.emails.add(list.get(i).getUser().getEmail());
        }
    }
}
