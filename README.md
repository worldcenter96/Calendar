# 일정 관리 앱 만들기

## 프로젝트 소개
- 일정을 추가, 수정, 삭제할 수 있는 일정 관리 앱을 만듭니다.
- 일정을 추가할 때는 날짜, 작성자명, 패스워드, 일정내용을 입력할 수 있습니다.
- 일정을 수정하거나 삭제할 때는 패스워드를 입력해야 합니다.
- 3 Layer Architecture에 따라 각 Layer의 목적에 맞게 개발해야 합니다.
- CRUD 필수 기능은 모두 JDBC를 사용해서 개발해야 합니다.

## 프로젝트 요구사항
Lv1. API 명세 및 ERD 작성
1. 와이어프레임<br>
![frame1.jpg](img%2Fframe1.jpg)<br>
![frame2.jpg](img%2Fframe2.jpg)

2. API 명세서

| 기능           | 메서드 | URL                          | 요청             | 응답                  |
|----------------|--------|-----------------------------|------------------|-----------------------|
| 일정 등록      | POST   | /api/schedule               | 요청 body        | 일정 등록 정보       |
| 일정 조회      | GET    | /api/schedule               | -                | 다건 응답 정보       |
| 날짜별 조회    | GET    | /api/schedule/date/{date}   | 요청 path        | 다건 응답 정보       |
| ID별 조회      | GET    | /api/schedule/id/{id}       | 요청 path        | 단건 응답 정보       |
| 일정 수정      | PUT    | /api/schedule/{id}          | 요청 path, 요청 body | 수정된 id 값      |
| 일정 삭제      | DELETE | /api/schedule/{id}          | 요청 path        | 삭제된 id 값         |

3. ERD

| 컬럼명      | 데이터타입   | 제약조건                         |
|-------------|---------------|----------------------------------|
| id          | INT           | AUTO_INCREMENT, PRIMARY KEY      |
| content     | TEXT          | NOT NULL                         |
| user        | VARCHAR(30)   | NOT NULL                         |
| pw          | VARCHAR(50)   | NOT NULL                         |
| date        | VARCHAR(20)   | NOT NULL                         |
| created_at  | DATETIME      | DEFAULT CURRENT_TIMESTAMP        |
| updated_at  | DATETIME      | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |


Lv2. 일정 생성 및 조회
1. 일정 생성(일정 작성하기)
- 일정 생성 시 포함되어야 할 데이터: 할일, 작성자명, 비밀번호, 작성/수정일
- 작성/수정일은 날짜와 시간을 모두 포함한 상태
- 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
- 최초 입력 시, 수정일은 작성일과 동일

2. 선택 일정 조회(선택한 일정 정보 불러오기)
- 선택한 일정 단건의 정보를 조회
- 일정의 고유 식별자(ID)를 사용하여 조회


Lv3. 선택한 일정 수정
1. 선택한 일정 수정
- 선택한 일정 내용 중 할일, 작성자명 만 수정 가능
- 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달
- 작성일은 변경할 수 없으며, 수정일은 수정 완료 시, 수정한 시점으로 변경

2. 선택한 일정 삭제
- 선택한 일정을 삭제
- 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달

## 애플리케이션 기능 구현
- **일정 등록**<br>
![schedule1.png](img%2Fschedule1.png)

- **일정 전체 조회**<br>
![schedule2.png](img%2Fschedule2.png)

- **날짜별 일정 조회**<br>
![schedule3.png](img%2Fschedule3.png)

- **id별 일정 조회**<br>
![schedule4.png](img%2Fschedule4.png)

- **일정 수정 및 삭제**<br>
![schedule5.png](img%2Fschedule5.png)
