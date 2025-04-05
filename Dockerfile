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

# Descargar dependencias para cachear en capas intermedias
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

# Copiar los certificados SSL al contenedor
COPY ./certs /app/certs

# Exponer los puertos para HTTP y HTTPS
EXPOSE 8080
EXPOSE 8443

# Comando de ejecución
ENTRYPOINT ["java", "-Djavax.net.ssl.keyStore=/app/certs/ssl-cert.p12", "-Djavax.net.ssl.keyStorePassword=yourpassword", "-jar", "app.jar"]
