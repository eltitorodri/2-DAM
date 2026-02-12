# Stage 1: Build stage
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos el pom y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y empaquetamos el .jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el archivo .jar generado en el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto y ejecutamos
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]