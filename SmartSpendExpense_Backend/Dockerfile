#
# Build Stage
#
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk
COPY --from=build app/target/SmartSpendExpense-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT-8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

