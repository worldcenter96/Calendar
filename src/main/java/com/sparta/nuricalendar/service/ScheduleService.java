package com.sparta.nuricalendar.service;

import com.sparta.nuricalendar.dto.ScheduleRequestDto;
import com.sparta.nuricalendar.dto.ScheduleResponseDto;
import com.sparta.nuricalendar.entity.Schedule;
import com.sparta.nuricalendar.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);
        return responseDto;
    }

    public List<ScheduleResponseDto> getSchedule() {

        // DB 조회
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();

    }

    public List<ScheduleResponseDto> getDateSchedule(String date) {

        // DB 조회
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findDate(date);

    }

    public ScheduleResponseDto getIdSchedule(long id) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule IsSchedule = scheduleRepository.findId(id);
        if (IsSchedule != null) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(IsSchedule);
            return responseDto;

        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }

    }

    public long editSchedule(long id, ScheduleRequestDto requestDto) {

        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.update(id, schedule);

    }

    public long deleteSchedule(long id) {

        // DB 데이터 삭제
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.delete(id);
    }
}
