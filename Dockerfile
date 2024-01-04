# Första steget: Maven build
FROM maven:3.8.7-openjdk-18-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Kör Maven clean och package utan att använda Maven-cachen
RUN mvn clean package -DskipTests --no-transfer-progress

# Andra steget: Förbered körningsmiljön
FROM openjdk:20-oracle
WORKDIR /app

# Kopiera den byggda JAR-filen från byggsteget till den nya containern
COPY --from=build /app/target/*.jar app.jar

# Exponera port 8080 för applikationen
EXPOSE 8080

# Ange startkommandot för att köra applikationen
ENTRYPOINT ["java","-jar","app.jar"]
