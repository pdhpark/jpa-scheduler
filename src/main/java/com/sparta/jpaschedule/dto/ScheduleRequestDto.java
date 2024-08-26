package com.sparta.jpaschedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private Long user_id;
    private String title;
    private String contents;
    private Long[] others_id;
}
