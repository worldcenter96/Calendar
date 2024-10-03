package com.sparta.nuricalendar.entity;

import com.sparta.nuricalendar.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Schedule {
    private long id;
    private String user;
    private String pw;
    private String content;
    private String date;

    public Schedule() {

    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.pw = requestDto.getPw();
        this.content = requestDto.getContent();
        this.date = requestDto.getDate();
    }
}
