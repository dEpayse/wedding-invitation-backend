# Wedding Invitation Backend

Spring Boot 기반 결혼 청첩장 웹 애플리케이션 백엔드

## 기술 스택

- **언어**: Kotlin 1.9.25
- **프레임워크**: Spring Boot 3.5.6
- **데이터베이스**: PostgreSQL (Supabase)
- **빌드 도구**: Gradle (Kotlin DSL)
- **Java 버전**: 17

## 시작하기

### 1. 사전 요구사항

- JDK 17 이상
- Supabase 프로젝트 (PostgreSQL 데이터베이스)

### 2. 환경 설정

`application-local.properties` 파일에 Supabase 비밀번호를 설정하세요:

```properties
# src/main/resources/application-local.properties
spring.datasource.password=your_actual_supabase_password
```

> **참고**: `application-local.properties`는 `.gitignore`에 포함되어 Git에 커밋되지 않습니다.

### 3. 애플리케이션 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun
```

### 4. IntelliJ IDEA에서 실행

그냥 실행하면 됩니다! `application-local.properties`에 비밀번호만 설정되어 있으면 자동으로 작동합니다.

**프로파일 변경**이 필요한 경우:
- `application.properties`에서 `spring.profiles.active=local` 또는 `prod`로 변경

## 개발 명령어

```bash
# 모든 테스트 실행
./gradlew test

# 특정 테스트 실행
./gradlew test --tests "com.example.weddinginvitation.*"

# 클린 빌드
./gradlew clean build

# JAR 파일 생성
./gradlew bootJar
```

## 데이터베이스 연결

이 프로젝트는 Supabase PostgreSQL 데이터베이스를 사용합니다.

- **Connection Pooling**: Spring Boot의 HikariCP가 자동으로 처리
- **JPA DDL**:
  - `local` 프로파일: `update` (자동으로 테이블 생성/업데이트)
  - `prod` 프로파일: `validate` (테이블 변경 안 함, 검증만)

## 프로파일 설정

### Local (개발 환경)
- `application.properties`: `spring.profiles.active=local`
- `application-local.properties`에 비밀번호 설정 필요
- SQL 쿼리 로그 출력

### Production (운영 환경)
- `application.properties`: `spring.profiles.active=prod`
- 환경 변수 `DATABASE_PASSWORD` 필요
- SQL 쿼리 로그 비활성화
- DDL 자동 변경 비활성화

## 프로젝트 구조

```
wedding-invitation/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/weddinginvitation/
│   │   └── resources/
│   │       ├── application.properties           # 공통 설정
│   │       ├── application-local.properties    # 로컬 개발용 (git 제외)
│   │       └── application-prod.properties     # 운영 환경용 (git 제외)
│   └── test/
├── build.gradle.kts
├── CLAUDE.md          # AI 코딩 어시스턴트 가이드
└── README.md
```

## 라이선스

MIT
