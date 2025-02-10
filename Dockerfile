FROM gradle:7.6-jdk17 AS builder
WORKDIR /app

# Gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew build --no-daemon --stacktrace --parallel || return 0

COPY . .
RUN ./gradlew bootJar --no-daemon

# JAR file
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar edu-ping.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/edu-ping.jar"]