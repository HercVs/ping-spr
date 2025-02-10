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

# For wait-for script
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

COPY wait-for-db.sh ./scripts/wait-for-db.sh
RUN chmod +x scripts/wait-for-db.sh
ENTRYPOINT ["./scripts/wait-for-db.sh"]