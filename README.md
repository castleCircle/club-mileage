
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


## API 호출 

초기 데이터 docker/mysql-init.sql 참고 (test경우 data.sql)


```
[리뷰 작성/수정/삭제 Event]

- POST : localhost:8080/events
- Body : 
{
    "type": "REVIEW",
    "action": "ADD" /*MOD,DELETE*/,
    "reviewId": "90490cfc-c0bc-471b-80e7-9fe4086c62d0",
    "content": "내용",
    "attachedPhotoIds": ["afbd7de1-b3e8-45e5-bff6-b7f34842ac1c"],
    "userId": "a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3",
    "placeId": "3b7d5e34-9176-45e9-b03a-7a465be2a270"
}


[포인트 조회]

- GET : localhost:8080/points/users/{userId}

[포인트 히스토리 조회]

- GET : localhost:8080/points/histories (사용자 전체)

- GET : localhost:8080/points/histories/{userId} (특정 사용자)

```