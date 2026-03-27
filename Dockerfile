# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests


# ---------- TEST STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app
COPY --from=builder /app /app

CMD ["mvn", "test", "-Dsurefire.suiteXmlFiles=src/test/java/resources/testng.xml"]