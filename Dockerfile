# Etapa 1: Construcción del JAR usando Gradle con JDK 17
FROM gradle:8.6.0-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

# Etapa 2: Imagen final solo con el JAR
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
