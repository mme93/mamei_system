FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /home/app

COPY libs/MDV_System-1.0.jar /home/app/libs/MDV_System-1.0.jar

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /home/app/target/massdatapool-0.0.1-SNAPSHOT.jar /usr/local/lib/massdatapool.jar
EXPOSE 9005
ENTRYPOINT ["java","-jar","/usr/local/lib/massdatapool.jar"]