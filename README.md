# MeetMe

Backend serwer for android application:  
https://github.com/Michuu93/meet-me-client

Technologies used:
- Spring Boot Webflux
- Spring Data R2DBC
- Reactive H2 Database
- Swagger for Webflux

**Build:**
```shell
./gradlew clean build
```
```shell
docker build -t michuu93/meetme-server .
```
**Run:**
```shell
docker run -d --name meetme-server -p 80:8080 -v "$PWD/data":/app/data michuu93/meetme-server
```
**Open:**  
http://localhost/swagger-ui.html