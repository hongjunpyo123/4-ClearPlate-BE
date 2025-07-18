# 환경을 위한 작은 실천 CLEARPLATE
본 레포지토리는 2025.07.12 ~  2025.07.13 9ithon 해커톤 백엔드 서버 입니다.
## Index
  - [프로젝트 소개](#프로젝트-소개)
  - [주요 기능](#주요-기능)
  - [기술 스택](#기술-스택)
  - [실행 방법](#실행-방법)
  - [데모](#데모)
  - [팀원 소개](#팀원-소개)
  - [프로젝트 구조](#프로젝트-구조)
  - [해커톤 회고](#해커톤-회고)
---
## 프로젝트 소개
**환경을 위한 작은 실천 CLEARPLATE**는 AI 기반 완식률 분석과 리워드 시스템을 통한 음식물 쓰레기 감축, 그리고 소상공인 지원을 동시에 실현하는 친환경 실천 플랫폼입니다

CLEARPLATE 는 아래와 같은 목적으로 시작하였습니다:

- 남기고, 버리고, 잊는 것이 너무 자연스러워진 우리의 식습관! 이것을 바꿔볼 수는 없을까?
- 음식을 남김없이 먹는 것에 '재미' 와 '보상' 을 더해 지속 가능한 행동을 유도할 수 있지 않을까?
- 어떻게 하면 음식을 판매하는 소상공인들을 우리 서비스에 끌어들일 수 있을까?

이러한 문제점들을 해결하기 위해, CLEARPLATE는 외식 상황에서 완식률을 AI로 분석하여 환경 실천을 리워드로 보상하는 플랫폼을 제공합니다.
사용자는 식사 전후 사진을 업로드하고 AI 기반 완식률을 분석하여 도장 및 포인트 적립을 통해 환경 보호를 실천할 수 있습니다
또한 완식을 달성한 매장에서는 도장이 부여되며 이를 통해 쿠폰을 교환하고 해당 매장의 재방문률을 높이는 결과를 얻을 수 있습니다


---
## 주요 기능
CLEARPLATE는 다음과 같은 주요 기능을 제공합니다:
1. QR 기반 식당 인증 - QR 코드를 통해 제휴 매장을 확인하고 위치를 인증합니다. 이용자가 해당 매장에 방문하였는지 확인하는 기능입니다.(악용 방지 장치)
2. AI 기반 완식률 분석 - 식사 전후 사진을 서비스에 업로드하면 AI가 완식률을 계산하고 퍼센트로 알려줍니다. 먹기 전과 먹은 후의 사진을 대조하여 악용을 방지하고 정확도를 올리는 방식으로 동작합니다.
3. 리워드 시스템 - 80% 이상 완식 시 도장과 포인트를 지급합니다. 도장 5개 수집 시 매장 할인 쿠폰을 제공하여 재방문을 유도하고, 포인트로는 환경 단체 기부나 CLP 굿즈 구매가 가능합니다.
4. 기록 및 분석 - 개인별 완식률, 주간 분석 등을 제공하여 사용자의 환경 실천 현황을 시각화합니다. 지속적인 동기부여를 위한 개인 맞춤형 피드백을 제공합니다.
5. 사회적 가치 연결 - 개인의 작은 실천이 환경 보호와 경제 활성화로 연결되는 구조를 통해 사회적 가치를 실현합니다.


---
## 기술 스택

### 백엔드
- ![Java](https://img.shields.io/badge/Java-17-orange)
- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.0-green)
- ![JPA](https://img.shields.io/badge/JPA-Hibernate-brightgreen)
- ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)

### 인프라
- ![AWS](https://img.shields.io/badge/AWS-EC2-orange)
- ![Nginx](https://img.shields.io/badge/Nginx-009639?style=flat&logo=nginx&logoColor=white)
- ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white)

### 개발 도구
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-2024.1-purple)
- ![Postman](https://img.shields.io/badge/Postman-API_Testing-orange)

### 협업 툴
- ![Git](https://img.shields.io/badge/Git-2.36-red)
- ![Figma](https://img.shields.io/badge/Figma-F24E1E?style=flat&logo=figma&logoColor=white)

### 문서화
- ![Swagger](https://img.shields.io/badge/Swagger-API_Docs-green)
- ![Notion](https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white)

  
---
## 실행 방법

### 사전 요구사항
- JDK 17
- Gradle 8.13
- MySQL 8.0
- Spring Boot 3.4.4

### 로컬 환경에서 실행
1. 레포지토리 클론
```bash
git clone https://github.com/9ITHON/4-ClearPlate-BE.git
cd clearplate
```

2. application.yml 설정
```bash
spring:
  config:
    import: optional:file:.env[.properties]

  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/${MYSQL_DATABASE}?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
    username: ${MYSQL_USERNAME}
    password: ${MYSQL_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

  security:
    oauth2:
      client:
        registration:
          kakao:
            client-id: ${KAKAO_CLIENT_ID}
            client-secret: ${KAKAO_CLIENT_SECRET}
            client-authentication-method: client_secret_post
            redirect-uri: ${KAKAO_REDIRECT_URI}
            scope: ${KAKAO_SCOPE}
            authorization-grant-type: authorization_code
        provider:
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize
            token-uri: https://kauth.kakao.com/oauth/token
            user-info-uri: https://kapi.kakao.com/v2/user/me
            user-name-attribute: id

jwt:
  accessTokenExpirationTime: 
  refreshTokenExpirationTime: 
  secretKey: ${JWT_SECRET_KEY}

server:
  servlet:
    encoding:
      charset: UTF-8
      force: true
      enabled: true

gemini:
  api-key: ${GEMINI_API_KEY}

springdoc:
  api-docs:
    version: OPENAPI_3_0

cloud:
  aws:
    s3:
      bucket: ${AWS_BUCKET_NAME}
    region:
      static: ${AWS_REGION}
    credentials:
      access-key: ${AWS_ACCESS_KEY}
      secret-key: ${AWS_SECRET_KEY}
```

3. 프로젝트 빌드 및 실행
```bash
./gradlew clean build
java -jar ../clearplate-0.0.1-SNAPSHOT.jar
```

---

현재 서비스는 해커톤 이후로 중단되었습니다.

### 스크린샷
### 스크린샷
| 기능 | 화면 |
|------|------|
| **앱 아이콘 & 로그인** | <img src="https://github.com/user-attachments/assets/58a2f0d1-74b0-4711-9cbc-c1d5625ada92" width="150"> <img src="https://github.com/user-attachments/assets/3b63604d-5bfb-467f-aad9-82509b6f521b" width="150"> |
| **메인 화면** | <img src="https://github.com/user-attachments/assets/ce20614a-84de-4bc0-81c3-e64c02e35ca8" width="150"> <img src="https://github.com/user-attachments/assets/a7945644-f1e2-4887-b398-5054a23be0ca" width="150"> |
| **메인 화면 (추가)** | <img src="https://github.com/user-attachments/assets/08003a80-6a44-46cd-a937-f2940693eabd" width="150"> |
| **식당 검색** | <img src="https://github.com/user-attachments/assets/35b72ac2-b13d-4938-9b81-decb5eeccbfe" width="150"> <img src="https://github.com/user-attachments/assets/fa491809-9378-4043-b1a6-8b6e64ddae73" width="150"> |
| **QR 인증** | <img src="https://github.com/user-attachments/assets/8f4cabce-4071-4245-bb26-4aa1b1722564" width="150"> <img src="https://github.com/user-attachments/assets/3cd3aa33-8bea-4b25-8d1b-98cf3f752504" width="150"> |
| **사진 업로드** | <img src="https://github.com/user-attachments/assets/58f83764-b35f-4246-b03d-ee2739139aaf" width="150"> <img src="https://github.com/user-attachments/assets/df118241-5807-49ac-8d2a-5dabffcf4bef" width="150"> |
| **사진 업로드 (완료)** | <img src="https://github.com/user-attachments/assets/7f6b2518-01fc-4f09-9d32-506616527dda" width="150"> |
| **완식률 분석** | <img src="https://github.com/user-attachments/assets/d33d2f56-0367-451b-aff4-f82faaf93655" width="150"> <img src="https://github.com/user-attachments/assets/3eb6df21-c4c5-4425-adbe-cb79e9951568" width="150"> |
| **마이페이지 & 쿠폰함** | <img src="https://github.com/user-attachments/assets/30673109-2c9b-4358-bfe2-710e433c2656" width="150"> <img src="https://github.com/user-attachments/assets/0ccaf618-86d4-40ea-b8cb-f26da449f70" width="150"> |
| **쿠폰 상세 & 포인트 내역** | <img src="https://github.com/user-attachments/assets/b727fe20-f82c-4e4d-8029-9f0e6d2e69b0" width="150"> <img src="https://github.com/user-attachments/assets/d8fd9128-ee26-4c3f-8e18-7314a56eb7b1" width="150"> |
| **포인트 사용 (1)** | <img src="https://github.com/user-attachments/assets/6cf704af-5594-4356-8a57-e05a05c9256f" width="150"> <img src="https://github.com/user-attachments/assets/cd4b0598-e722-437a-b0c5-e0ea1efe3307" width="150"> |
| **포인트 사용 (2)** | <img src="https://github.com/user-attachments/assets/38d0fc06-48f2-45b4-a720-55e6e2006f89" width="150"> <img src="https://github.com/user-attachments/assets/ef756a59-0f16-4d78-82f0-c2de8246a5ba" width="150"> |






---
## 팀원 소개

### Frontend Developer
<img src="https://github.com/kgs9843.png" width="100" height="100" style="border-radius: 50%;" />

- GitHub: [@kgs9843](https://github.com/kgs9843)

<img src="https://avatars.githubusercontent.com/u/155722710?v=4" width="100" height="100" style="border-radius: 50%;" />

- GitHub: [@ndurumi922](https://avatars.githubusercontent.com/u/155722710?v=4)
---
### Backend Developer
<img src="https://github.com/hongjunpyo123.png" width="100" height="100" style="border-radius: 50%;" />

- GitHub: [@hongjunpyo123](https://github.com/hongjunpyo123)

<img src="https://avatars.githubusercontent.com/u/87798442?v=4" width="100" height="100" style="border-radius: 50%;" />

- GitHub: [@drghdtjr](https://github.com/drghdtjr)


---
## 프로젝트 구조
```bash
clearplate/
├── src/
│   ├── main/
│   │   ├── java/com/qithon/clearplate/
│   │   │   ├── ClearplateApplication.java
│   │   │   ├── domain/
│   │   │   │   ├── CLPrestaurant/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   ├── request/
│   │   │   │   │   │   └── response/
│   │   │   │   │   ├── entity/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── service/
│   │   │   │   │   └── vo/
│   │   │   │   ├── coupon/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   ├── request/
│   │   │   │   │   │   └── response/
│   │   │   │   │   ├── entity/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── service/
│   │   │   │   ├── food/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── dto/
│   │   │   │   │   ├── entity/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── service/
│   │   │   │   ├── point/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── dto/
│   │   │   │   │   │   └── request/
│   │   │   │   │   └── service/
│   │   │   │   ├── stamp/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── entity/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── service/
│   │   │   │   └── user/
│   │   │   │       ├── controller/
│   │   │   │       ├── dto/
│   │   │   │       │   ├── request/
│   │   │   │       │   └── response/
│   │   │   │       ├── entity/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   ├── global/
│   │   │   │   ├── common/
│   │   │   │   │   ├── dto/response/
│   │   │   │   │   └── threadlocal/
│   │   │   │   ├── config/
│   │   │   │   ├── exception/
│   │   │   │   ├── oauth2/
│   │   │   │   └── security/
│   │   │   │       ├── config/
│   │   │   │       ├── core/
│   │   │   │       └── jwt/
│   │   │   └── infra/
│   │   │       ├── Aws/
│   │   │       │   └── awsTestContoller/
│   │   │       └── gemini/
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   └── response/
│   │   │           └── geminiTestController/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/qithon/clearplate/
│           └── ClearplateApplicationTests.java
├── build.gradle
├── docker-compose.dev.yml
├── docker-compose.prod.yml
├── Dockerfile
└── settings.gradle
```


---
## 해커톤 회고

### 느낀 점
- 정말 다양한 사람과 협업을 해봐야함을 느꼈다. 특히 같은 포지션의 개발자들과의 협업 경험이 매우 중요함을 느낀 대회였다.
- 내가 작성한 코드가 나만 보는것이 아님을 깨닫는 순간 사소한 함수명, 아주 사소한 변수명 까지도 한번 더 생각해보게 되었다.
- 특히 높은 효율을 위해서는 나의 코드가 직관적으로 이해될 수 있어야 함을 느꼈다(안 그러면 팀원이 이를 이해하는데 너무나 많은 시간이 소요된다)
- 같은 포지션과의 협업 경험은 나의 볼품없는 코드를 개선시키고 더욱 직관적으로 만든다. 

### 개선할 점
- 제출 5분전에 CI/CD 파이프라인이 갑자기 먹통되는 이슈가 발생했다. 아직도 이 이유를 찾지 못했고 제출 시간이 지나고 나서 다시 한번 시도했을 때 동작하였다.
- 절대 제출 5분전에 뭔가를 하려고 하지말자.. 도저히 수습이 안된다
---
<img width="1274" height="717" alt="스크린샷 2025-07-18 오후 10 38 44" src="https://github.com/user-attachments/assets/cf3bd071-7fe9-4ac1-bcbc-94cebc5ba655" />
<img width="1274" height="717" alt="스크린샷 2025-07-18 오후 10 39 18" src="https://github.com/user-attachments/assets/16f63e58-7e6f-4ab1-8bfc-6f1c398daef9" />
<img width="1274" height="717" alt="스크린샷 2025-07-18 오후 10 39 39" src="https://github.com/user-attachments/assets/7c757e22-cc3b-4a1e-bb4e-d5a6c269dd50" />
<img width="1274" height="717" alt="스크린샷 2025-07-18 오후 10 40 26" src="https://github.com/user-attachments/assets/5c8b83be-7296-49ce-bfb1-72fd179e35eb" />
