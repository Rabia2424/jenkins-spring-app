FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/*.jar myapp.jar
ENTRYPOINT ["java","-jar","myapp.jar"]