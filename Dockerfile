FROM openjdk:17
WORKDIR /app
COPY target/*.jar myapp.jar
ENTRYPOINT ["java","-jar","myapp.jar"]