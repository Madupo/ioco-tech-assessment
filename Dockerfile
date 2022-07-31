FROM openjdk:8
ADD target/ioco-assessment-backend.jar .
ENTRYPOINT ["java", "-jar","ioco-assessment-backend.jar"]
EXPOSE 5050
