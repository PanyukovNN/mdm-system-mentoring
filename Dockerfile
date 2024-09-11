FROM amazoncorretto:17.0.7-alpine

COPY ./build/libs/mdm-system-mentoring.jar ./mdm-system-mentoring.jar

ENV SPRING_PROFILES_ACTIVE=docker
ENV TZ=Europe/Moscow

EXPOSE 8020

ENTRYPOINT ["java", "-jar", "./mdm-system-mentoring.jar"]