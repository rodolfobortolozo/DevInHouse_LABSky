FROM eclipse-temurin:17-jdk-alpine

COPY /target/*.jar /opt/app/lab-sky.jar

LABEL maintainer="rodolfobortolozo@gmail.com"

ENTRYPOINT ["java","-jar","/opt/app/lab-sky.jar"]

EXPOSE 8081