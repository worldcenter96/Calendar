package com.sparta.nuricalendar.dto;

import com.sparta.nuricalendar.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private long id;
    private String user;
    private String pw;
    private String content;
    private String date;
    private String createDate;
    private String updateDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.user = schedule.getUser();
        this.pw = schedule.getPw();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(long id, String content, String user, String pw, String date, String createDate, String updateDate) {
        this.id = id;
        this.user = user;
        this.pw = pw;
        this.content = content;
        this.date = date;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
