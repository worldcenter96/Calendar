package com.sparta.nuricalendar.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String user;
    private String pw;
    private String content;
    private String date;
}
