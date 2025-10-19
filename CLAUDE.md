# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요
이 프로젝트는 Kotlin과 Spring Boot를 사용하는 결혼 청첩장 웹 애플리케이션입니다.

## 기술 스택
- **언어**: Kotlin 1.9.25
- **프레임워크**: Spring Boot 3.5.6
- **데이터베이스**: PostgreSQL
- **빌드 도구**: Gradle (Kotlin DSL)
- **Java 버전**: 17

## 주요 명령어

### 빌드 및 실행
```bash
# 애플리케이션 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun

# 개발 모드로 실행 (DevTools 활성화)
./gradlew bootRun --args='--spring.profiles.active=dev'

# JAR 파일 생성
./gradlew bootJar
```

### 테스트
```bash
# 모든 테스트 실행
./gradlew test

# 특정 테스트 클래스 실행
./gradlew test --tests com.example.weddinginvitation.WeddingInvitationApplicationTests

# 특정 테스트 메서드 실행
./gradlew test --tests "com.example.weddinginvitation.*TestClass.testMethodName"
```

### 클린 및 빌드
```bash
# 클린 빌드
./gradlew clean build

# 빌드 캐시 없이 빌드
./gradlew build --no-build-cache
```

## 프로젝트 구조
```
wedding-invitation/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/weddinginvitation/
│   │   │       └── WeddingInvitationApplication.kt  # 메인 애플리케이션 클래스
│   │   └── resources/
│   │       └── application.properties  # 애플리케이션 설정
│   └── test/
│       └── kotlin/
│           └── com/example/weddinginvitation/
│               └── WeddingInvitationApplicationTests.kt
├── build.gradle.kts  # Gradle 빌드 설정
└── settings.gradle.kts
```

## 아키텍처 가이드

### Spring Boot 설정
- **패키지 구조**: `com.example.weddinginvitation` 베이스 패키지
- **JPA**: Kotlin JPA 플러그인 사용, Entity/MappedSuperclass/Embeddable 클래스는 자동으로 open
- **데이터베이스**: PostgreSQL 사용 (application.properties에서 연결 정보 설정 필요)

### Kotlin 설정
- **컴파일러 옵션**: JSR-305 strict 모드 활성화 (`-Xjsr305=strict`)
- **JPA Entity**: `allOpen` 플러그인으로 Entity 클래스 자동 open 처리

### 의존성
- **웹**: Spring Web (RESTful API)
- **데이터**: Spring Data JPA + PostgreSQL
- **개발 도구**: Spring Boot DevTools (자동 재시작)
- **JSON**: Jackson Kotlin Module (Kotlin 데이터 클래스 직렬화)

## 데이터베이스 설정
이 프로젝트는 Supabase PostgreSQL을 사용합니다.

### 연결 정보
- **Host**: aws-1-ap-northeast-2.pooler.supabase.com:5432
- **Database**: postgres
- **Username**: postgres.npgqwlvrvgwsjxwlcrvf

### 프로파일 설정
- **Local**: `application-local.properties`에 비밀번호 설정
- **Production**: 환경 변수 `DATABASE_PASSWORD` 사용

```properties
# application-local.properties (Git에서 제외됨)
spring.datasource.password=your_supabase_password
```

## 개발 시 유의사항
- Entity 클래스는 `@Entity` 애노테이션만 추가하면 자동으로 open 처리됩니다
- Kotlin 데이터 클래스를 사용할 때 Jackson이 자동으로 처리합니다
- DevTools가 포함되어 있어 코드 변경 시 자동으로 애플리케이션이 재시작됩니다
