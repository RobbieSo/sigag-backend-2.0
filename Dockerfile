# === Etapa 1: Compilación con Gradle ===
FROM gradle:8.6.0-jdk17-alpine AS builder

WORKDIR /app

# Copiamos todo el proyecto al contenedor de build
COPY --chown=gradle:gradle . .

# Damos permisos al wrapper
RUN chmod +x ./gradlew

# Ejecutamos el build para generar el .jar
RUN ./gradlew build --no-daemon

# === Etapa 2: Imagen ligera solo con el .jar ===
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto de exposición (coincide con application.properties)
EXPOSE 8082

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
