FROM maven:3.8.6-openjdk-18-slim AS build-stage
ADD . /app/
WORKDIR /app/
RUN mvn clean
RUN mvn install
FROM amd64/openjdk:17.0-oracle AS run-stage
ADD . /app/
WORKDIR /app/
COPY --from=build-stage /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","app.jar"]