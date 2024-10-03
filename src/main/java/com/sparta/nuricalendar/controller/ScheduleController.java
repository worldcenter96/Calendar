package com.sparta.nuricalendar.controller;

import com.sparta.nuricalendar.dto.ScheduleRequestDto;
import com.sparta.nuricalendar.dto.ScheduleResponseDto;
import com.sparta.nuricalendar.entity.Schedule;
import com.sparta.nuricalendar.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Controller
public class ScheduleController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @GetMapping("/check")
    public String check() {
        return "redirect:/check.html";
    }

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/schedule")
    @ResponseBody
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);

    }

    @GetMapping("/api/schedule")
    @ResponseBody
    public List<ScheduleResponseDto> getSchedule() {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSchedule();

    }

    @GetMapping("/api/schedule/date/{date}")
    @ResponseBody
    public List<ScheduleResponseDto> getDateSchedule(@PathVariable String date) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getDateSchedule(date);

    }

    @GetMapping("/api/schedule/id/{id}")
    @ResponseBody
    public ScheduleResponseDto getIdSchedule(@PathVariable long id) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getIdSchedule(id);

    }

    @PutMapping("/api/schedule/{id}")
    @ResponseBody
    public long editSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.editSchedule(id, requestDto);

    }

    @DeleteMapping("/api/schedule/{id}")
    @ResponseBody
    public long deleteSchedule(@PathVariable long id) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.deleteSchedule(id);

    }


}
