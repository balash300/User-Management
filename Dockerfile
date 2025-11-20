FROM gradle:8.7-jdk21-alpine AS BUILDER
WORKDIR /app
COPY . .
RUN gradle clean build -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=BUILDER /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]