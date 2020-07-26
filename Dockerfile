FROM openjdk:12
LABEL maintainer=“raj_upadhyay@hotmail.com”
WORKDIR /app
COPY lib/field_data_api-0.0.1-SNAPSHOT.jar /app/field_data_api.jar
ENTRYPOINT ["java","-jar","field_data_api.jar"]