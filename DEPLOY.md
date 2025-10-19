# 미니 PC 배포 가이드

## 방법 1: JAR 파일 직접 실행 (가장 간단)

### 1단계: 현재 PC에서 JAR 파일 빌드

```bash
# Windows
gradlew.bat clean bootJar

# Linux/Mac
./gradlew clean bootJar
```

빌드된 JAR 파일 위치: `build/libs/wedding-invitation-0.0.1-SNAPSHOT.jar`

---

### 2단계: 미니 PC 준비

#### Java 설치 확인/설치

**Linux (Ubuntu/Debian):**
```bash
# Java 17 설치
sudo apt update
sudo apt install openjdk-17-jre

# 확인
java -version
```

**Windows:**
- Oracle JDK 17 다운로드: https://www.oracle.com/java/technologies/downloads/#java17
- 또는 OpenJDK: https://adoptium.net/

---

### 3단계: 파일 전송

#### application-local.properties 수정
미니 PC에서 사용할 `application-local.properties` 파일 준비:

```properties
# application-local.properties
spring.datasource.password=YOUR_SUPABASE_PASSWORD
```

#### 파일 전송 방법

**방법 A: USB/외장하드**
- JAR 파일과 `application-local.properties`를 USB에 복사
- 미니 PC에 옮기기

**방법 B: scp (Linux)**
```bash
# 현재 PC에서 실행
scp build/libs/wedding-invitation-0.0.1-SNAPSHOT.jar user@mini-pc-ip:/home/user/app/
scp application-local.properties user@mini-pc-ip:/home/user/app/
```

**방법 C: GitHub**
```bash
# 미니 PC에서
git clone https://github.com/dEpayse/wedding-invitation-backend.git
cd wedding-invitation-backend
./gradlew clean bootJar
```

---

### 4단계: 미니 PC에서 실행

#### 디렉토리 구조
```
/home/user/app/
├── wedding-invitation-0.0.1-SNAPSHOT.jar
└── application-local.properties
```

#### 실행 명령어

**기본 실행:**
```bash
java -jar wedding-invitation-0.0.1-SNAPSHOT.jar
```

**백그라운드 실행:**
```bash
# nohup 사용
nohup java -jar wedding-invitation-0.0.1-SNAPSHOT.jar > app.log 2>&1 &

# 프로세스 확인
ps aux | grep wedding-invitation

# 로그 확인
tail -f app.log
```

**메모리 설정 (옵션):**
```bash
java -Xmx512m -Xms256m -jar wedding-invitation-0.0.1-SNAPSHOT.jar
```

---

### 5단계: 접속 확인

#### 미니 PC에서 확인:
```bash
curl http://localhost:8080/api/v1/guests
```

#### 같은 네트워크의 다른 PC에서 접속:
```
http://미니PC-IP주소:8080/api/v1/guests
```

미니 PC IP 주소 확인:
```bash
# Linux
ip addr show
# 또는
hostname -I

# Windows
ipconfig
```

---

## 방법 2: systemd 서비스로 등록 (자동 시작)

### Linux 전용

#### 1. 서비스 파일 생성
```bash
sudo nano /etc/systemd/system/wedding-invitation.service
```

#### 2. 내용 입력
```ini
[Unit]
Description=Wedding Invitation Backend
After=network.target

[Service]
Type=simple
User=your-username
WorkingDirectory=/home/your-username/app
ExecStart=/usr/bin/java -jar /home/your-username/app/wedding-invitation-0.0.1-SNAPSHOT.jar
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

#### 3. 서비스 시작
```bash
# 서비스 활성화
sudo systemctl enable wedding-invitation

# 서비스 시작
sudo systemctl start wedding-invitation

# 상태 확인
sudo systemctl status wedding-invitation

# 로그 확인
sudo journalctl -u wedding-invitation -f
```

#### 4. 서비스 관리 명령어
```bash
# 중지
sudo systemctl stop wedding-invitation

# 재시작
sudo systemctl restart wedding-invitation

# 부팅 시 자동 시작 비활성화
sudo systemctl disable wedding-invitation
```

---

## 방법 3: Docker 사용

### Dockerfile 사용 (이미 있음)

```bash
# 미니 PC에서
git clone https://github.com/dEpayse/wedding-invitation-backend.git
cd wedding-invitation-backend

# Docker 이미지 빌드
docker build -t wedding-invitation .

# 실행
docker run -d \
  -p 8080:8080 \
  -e DATABASE_PASSWORD=your_supabase_password \
  --name wedding-invitation-app \
  wedding-invitation
```

---

## 네트워크 설정 (외부 접속)

### 방화벽 열기

**Linux (UFW):**
```bash
sudo ufw allow 8080
```

**Windows:**
- Windows Defender 방화벽 설정에서 8080 포트 허용

### 라우터 포트포워딩 (외부 인터넷 접속 필요 시)
1. 라우터 관리 페이지 접속
2. 포트포워딩 설정
3. 외부 포트: 8080 → 내부 IP: 미니PC-IP:8080

---

## 문제 해결

### 포트가 이미 사용 중인 경우
```bash
# 포트 사용 프로세스 확인
# Linux
sudo lsof -i :8080
sudo netstat -tulpn | grep 8080

# Windows
netstat -ano | findstr :8080
```

### 다른 포트 사용
`application.properties`에 추가:
```properties
server.port=9090
```

### 로그 레벨 조정
```properties
logging.level.root=INFO
logging.level.com.example.weddinginvitation=DEBUG
```

---

## 추천 배포 방법

### 개발/테스트: 방법 1 (JAR 직접 실행)
- 가장 간단하고 빠름
- 테스트 용도로 적합

### 운영 환경: 방법 2 (systemd 서비스) 또는 방법 3 (Docker)
- 자동 시작/재시작
- 안정적인 운영
- 관리가 편함
