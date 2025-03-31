# Etapa 1: Construcción del proyecto
FROM gradle:8.6-jdk17 as builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .

# Usamos el wrapper de Gradle incluido en el proyecto
RUN ./gradlew build --no-daemon

# Etapa 2: Imagen final con solo el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
