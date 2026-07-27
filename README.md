# Spring Boot Book Search & Community

Spring Boot와 MySQL을 기반으로 구현한 **도서 검색 및 커뮤니티 웹 애플리케이션**입니다.

도서 정보를 조회하고 제목으로 검색할 수 있으며, 사용자는 회원가입과 로그인 후 게시글과 댓글을 작성할 수 있습니다.
Spring MVC 구조와 JPA 연관관계를 학습하고 실제 웹 서비스의 기본 흐름을 구현하는 것을 목표로 개발했습니다.

---

## 주요 기능

### 도서 기능

* 전체 도서 목록 조회
* 도서 상세 정보 조회
* 도서 제목 키워드 검색
* 저자 및 출판사 정보 표시

현재 도서 검색은 외부 API가 아닌 **MySQL 데이터베이스에 저장된 도서 데이터**를 대상으로 동작합니다.

### 사용자 기능

* 회원가입
* 로그인
* 로그아웃
* 세션 기반 로그인 상태 유지
* 로그인 사용자 정보 표시

### 게시판 기능

* 게시글 목록 조회
* 게시글 상세 조회
* 게시글 작성
* 게시글 수정
* 게시글 삭제
* 작성자 기반 수정 및 삭제 권한 확인

### 댓글 기능

* 게시글별 댓글 조회
* 댓글 작성
* 댓글 삭제
* 댓글 작성자 정보 표시

---

## 기술 스택

### Backend

* Java 17
* Spring Boot 3.3.5
* Spring MVC
* Spring Data JPA
* Hibernate
* Maven

### Frontend

* HTML5
* CSS3
* Thymeleaf

### Database

* MySQL

### Development Tools

* Spring Tool Suite
* Git
* GitHub

---

## 프로젝트 구조

```text
src
├─ main
│  ├─ java
│  │  └─ com.example.demo
│  │     ├─ board
│  │     │  ├─ Board.java
│  │     │  ├─ BoardController.java
│  │     │  ├─ BoardRepository.java
│  │     │  └─ BoardService.java
│  │     ├─ book
│  │     │  ├─ Book.java
│  │     │  ├─ BookController.java
│  │     │  ├─ BookRepository.java
│  │     │  └─ BookService.java
│  │     ├─ comment
│  │     │  ├─ Comment.java
│  │     │  ├─ CommentController.java
│  │     │  └─ CommentRepository.java
│  │     ├─ home
│  │     ├─ library
│  │     ├─ user
│  │     │  ├─ User.java
│  │     │  ├─ UserController.java
│  │     │  ├─ UserRepository.java
│  │     │  └─ UserService.java
│  │     └─ StsApplication.java
│  └─ resources
│     └─ templates
│        ├─ board
│        ├─ book
│        ├─ library
│        ├─ user
│        └─ home.html
└─ test
```

---

## 데이터 관계

프로젝트에서는 다음과 같은 JPA 연관관계를 사용합니다.

```text
User 1 ─── N Board
User 1 ─── N Comment
Board 1 ─── N Comment
```

* 한 명의 사용자는 여러 게시글을 작성할 수 있습니다.
* 한 명의 사용자는 여러 댓글을 작성할 수 있습니다.
* 하나의 게시글에는 여러 댓글이 작성될 수 있습니다.
* 게시글을 삭제하면 해당 게시글의 댓글도 함께 삭제됩니다.

---

## 실행 환경

프로젝트를 실행하려면 다음 프로그램이 필요합니다.

* Java 17 이상
* MySQL 8.0 이상
* Git

Maven은 프로젝트에 포함된 Maven Wrapper를 사용하므로 별도로 설치하지 않아도 됩니다.

---

## 실행 방법

### 1. 저장소 복제

```bash
git clone https://github.com/imminjudev/spring-homepage.git
cd spring-homepage
```

### 2. MySQL 데이터베이스 생성

MySQL에 접속한 뒤 프로젝트에서 사용할 데이터베이스를 생성합니다.

```sql
CREATE DATABASE spring_homepage
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

### 3. 데이터베이스 설정 파일 생성

다음 경로에 `application.properties` 파일을 생성합니다.

```text
src/main/resources/application.properties
```

파일에 다음 내용을 입력합니다.

```properties
spring.application.name=spring-homepage

spring.datasource.url=jdbc:mysql://localhost:3306/spring_homepage?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.thymeleaf.cache=false
```

다음 값을 자신의 MySQL 환경에 맞게 변경해야 합니다.

```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

`application.properties`에는 데이터베이스 비밀번호가 포함될 수 있으므로 Git 저장소에는 업로드하지 않습니다.

### 4. 애플리케이션 실행

#### Windows

```bash
mvnw.cmd spring-boot:run
```

#### macOS 또는 Linux

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

IDE에서 실행하는 경우에는 다음 클래스의 `main()` 메서드를 실행합니다.

```text
src/main/java/com/example/demo/StsApplication.java
```

### 5. 웹사이트 접속

애플리케이션이 정상적으로 실행되면 브라우저에서 다음 주소로 접속합니다.

```text
http://localhost:8080
```

---

## 테스트용 도서 데이터 추가

현재 도서 검색은 데이터베이스에 저장된 도서를 대상으로 동작합니다.

애플리케이션을 한 번 실행하여 `books` 테이블이 생성된 이후, MySQL에서 다음과 같이 테스트 데이터를 추가할 수 있습니다.

```sql
INSERT INTO books (title, author, publisher)
VALUES
    ('클린 코드', '로버트 C. 마틴', '인사이트'),
    ('객체지향의 사실과 오해', '조영호', '위키북스'),
    ('스프링 부트와 AWS로 혼자 구현하는 웹 서비스', '이동욱', '프리렉'),
    ('혼자 공부하는 컴퓨터 구조와 운영체제', '강민철', '한빛미디어');
```

데이터를 추가한 후 다음 페이지에서 도서 목록과 검색 기능을 확인할 수 있습니다.

```text
http://localhost:8080/book/list
http://localhost:8080/book/search
```

---

## 주요 URL

| 기능         | HTTP Method | URL                    |
| ---------- | ----------- | ---------------------- |
| 메인 페이지     | GET         | `/`                    |
| 회원가입 페이지   | GET         | `/user/signup`         |
| 회원가입 처리    | POST        | `/user/signup`         |
| 로그인 페이지    | GET         | `/user/login`          |
| 로그인 처리     | POST        | `/user/login`          |
| 로그아웃       | GET         | `/user/logout`         |
| 도서 목록      | GET         | `/book/list`           |
| 도서 검색      | GET         | `/book/search`         |
| 도서 상세      | GET         | `/book/{id}`           |
| 게시글 목록     | GET         | `/board/list`          |
| 게시글 작성 페이지 | GET         | `/board/write`         |
| 게시글 작성     | POST        | `/board/write`         |
| 게시글 상세     | GET         | `/board/{id}`          |
| 게시글 수정     | POST        | `/board/edit/{id}`     |
| 게시글 삭제     | POST        | `/board/delete/{id}`   |
| 댓글 작성      | POST        | `/comment/write`       |
| 댓글 삭제      | POST        | `/comment/delete/{id}` |

---

## 구현 과정에서 학습한 내용

* Spring MVC의 요청 및 응답 처리 구조
* Controller, Service, Repository 계층 분리
* Spring Data JPA를 이용한 데이터베이스 접근
* JPA Entity 연관관계 설정
* Thymeleaf를 이용한 서버 사이드 렌더링
* HttpSession을 이용한 로그인 상태 관리
* 게시글 및 댓글의 작성자 권한 확인
* Maven 기반 Spring Boot 프로젝트 구성
* Git과 GitHub를 이용한 버전 관리

---

## 향후 개선 계획

* Spring Security 적용
* 비밀번호 암호화
* 회원가입 입력값 검증
* 사용자 아이디 중복 검사
* 게시글과 댓글 예외 처리
* 도서 등록 및 관리 기능
* 외부 도서 검색 API 연동
* 게시글 검색 및 페이지네이션
* 단위 테스트와 통합 테스트 작성
* 공통 화면 레이아웃 및 CSS 분리
* 배포 환경 구축

---

## 개발자

**임민주**

* GitHub: `imminjudev`
