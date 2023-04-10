
## 사용 기술

```
- Java 11
- SpringBoot 2.7.10
  - JPA , Querydsl
- Gradle
- MySQL 8.0.28
- docker
```

## 실행방법

1. MySQL 컨테이너 실행
```
$ docker compose up
```

2. Application 실행
```
$ ./gradlew build && java -jar build/libs/club-mileage-0.0.1-SNAPSHOT.jar
```
