### Build ###
FROM gradle:6.0.1-jdk8 AS BUILDER
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build

### Run ###
FROM adoptopenjdk/openjdk11:jdk-11.0.3_7-slim
WORKDIR /app
COPY --from=BUILDER /home/gradle/src/build/libs/meetme-server*.jar meetme-server.jar
EXPOSE 8080
CMD ["java","-jar","meetme-server.jar"]