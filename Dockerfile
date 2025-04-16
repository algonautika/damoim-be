# Build Stage
FROM eclipse-temurin:21 AS build
WORKDIR /app

COPY . .

# Gradle 빌드 (테스트 생략 예시)
RUN ./gradlew clean build -x test

# Run Stage
FROM eclipse-temurin:21
WORKDIR /app

# Build Stage에서 생성된 jar 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
