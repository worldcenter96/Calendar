package com.sparta.nuricalendar.repository;

import com.sparta.nuricalendar.dto.ScheduleResponseDto;
import com.sparta.nuricalendar.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;


    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Schedule save(Schedule schedule) {
        // PK를 가져오기 위한 객체 생성
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (content, user, pw, date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getContent());
            preparedStatement.setString(2, schedule.getUser());
            preparedStatement.setString(3, schedule.getPw());
            preparedStatement.setString(4, schedule.getDate());
            return  preparedStatement;
        }, keyHolder);

        long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL의 결과로 받아온 Memo 데이터들을 ScheduleResponseDto 타입으로 변환해줄 메서드
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String user = rs.getString("user");
                String pw = rs.getString("pw");
                String date = rs.getString("date");
                String createDate = rs.getString("created_at");
                String updateDate = rs.getString("updated_at");
                return new ScheduleResponseDto(id, content, user, pw, date, createDate, updateDate);
            }
        });
    }


    public List<ScheduleResponseDto> findDate(String date) {
        String sql = "SELECT * FROM schedule WHERE date='" + date + "'";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String user = rs.getString("user");
                String pw = rs.getString("pw");
                String date = rs.getString("date");
                String createDate = rs.getString("created_at");
                String updateDate = rs.getString("updated_at");
                return new ScheduleResponseDto(id, content, user, pw, date, createDate, updateDate);
            }
        });
    }

    public Schedule findId(long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setUser(resultSet.getString("user"));
                schedule.setPw(resultSet.getString("pw"));
                schedule.setDate(resultSet.getString("date"));
                schedule.setContent(resultSet.getString("content"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }

    public long update(long id, Schedule schedule) {
        String sql = "UPDATE schedule SET content = ?, updated_at = NOW() WHERE id = ?";
        jdbcTemplate.update(sql, schedule.getContent(), id);
        return id;
    }


    public long delete(long id) {
        String sql = "DELETE FROM schedule WHERE id=?";
        jdbcTemplate.update(sql, id);
        return id;
    }
}
