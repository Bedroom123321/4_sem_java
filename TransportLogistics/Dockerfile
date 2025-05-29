FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn

RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package -DskipTests -B


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xmx300m"
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]