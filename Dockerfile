# Etapa 1: Construcción del JAR
FROM eclipse-temurin:17-jdk AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Gradle y scripts de ejecución
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x gradlew

# Descarga de dependencias para cachear en capas intermedias
RUN ./gradlew dependencies --no-daemon

# Copiar el código fuente
COPY src src

# Construcción de la aplicación
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Imagen final con solo el JAR
FROM eclipse-temurin:17-jre

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]