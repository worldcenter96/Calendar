// DB 생성
CREATE DATABASE calendar;

// 테이블 생성
CREATE TABLE schedule (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT NOT NULL,
                          user VARCHAR(30) NOT NULL,
                          pw VARCHAR(50) NOT NULL,
                          date VARCHAR(20) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);