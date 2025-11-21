# ---- Build Stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Gradle build
RUN ./gradlew bootJar --no-daemon

# ---- Run Stage ----
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

# Uygulamayı çalıştır
ENTRYPOINT ["java","-jar","/app/app.jar"]