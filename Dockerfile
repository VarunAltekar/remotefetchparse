# Basic
#FROM amazoncorretto:11-alpine-jdk
#ADD target/remotefetchparse-0.0.1-SNAPSHOT.jar remotefetchparse-0.0.1-SNAPSHOT
#ENTRYPOINT ["sh", "-c", "java -jar /remotefetchparse-0.0.1-SNAPSHOT"]

# Advance Docker Caching
FROM amazoncorretto:11-alpine-jdk
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.CountrycodesApplication"]