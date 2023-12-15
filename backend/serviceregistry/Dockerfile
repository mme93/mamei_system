FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package -DskipTests
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build /home/app/target/serviceregistry-0.0.1-SNAPSHOT.jar /usr/local/lib/serviceregistry.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","-s /usr/local/lib/serviceregistry.jar"]
