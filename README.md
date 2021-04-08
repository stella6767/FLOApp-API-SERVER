# FLO 앱 서버 (클론 코딩)


### 설명

자체적으로 DB를 구축하여 만든 FLO앱 전용 스프링부트 RESTAPI서버


### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- Spring Validation
- Spring AOP
- RestDoc


#### 추가 의존성

``` 추가 의존성

		<dependency>
			<groupId>org.springframework.restdocs</groupId>
			<artifactId>spring-restdocs-mockmvc</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.restdocs</groupId>
			<artifactId>spring-restdocs-asciidoctor</artifactId>
			<version>2.0.2.RELEASE</version>
		</dependency>
```


### 데이터베이스

```DB 설정
create user 'flo'@'%' identified by 'flo1234';
GRANT ALL PRIVILEGES ON *.* TO 'flo'@'%';
create database flo;
```


### yml.설정


```yml.설정
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true


spring:

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/flo?serverTimezone=Asia/Seoul
    username: flo
    password: flo1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#    show-sql: true
#    properties:
#      hibernate.format_sql: true

  jackson:
    serialization:
      fail-on-empty-beans: false
```


### 모델

- Song
- PlaySong
- Reply
- Storage
- StorageSong
- User
- ErrorLog


### 시큐리티 설정

![image](https://user-images.githubusercontent.com/65489223/114041529-96233f80-98bf-11eb-8775-b911425feb5d.png)


### 예외처리

![image](https://user-images.githubusercontent.com/65489223/114042129-1d70b300-98c0-11eb-9f7d-005afc1c6ef1.png)


### AOP Validation 처리

![image](https://user-images.githubusercontent.com/65489223/114041570-9f141100-98bf-11eb-8f93-cb4c0e54eb11.png)




### 블로그 주소


<https://blog.naver.com/PostView.nhn?blogId=alsrb9434&logNo=222265727309&categoryNo=35&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1>




### 동영상 주소




### PPT 주소




