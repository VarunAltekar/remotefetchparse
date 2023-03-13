FROM amazoncorretto:11-alpine-jdk
ADD target/remotefetchparse-0.0.1-SNAPSHOT.jar remotefetchparse-0.0.1-SNAPSHOT
ENTRYPOINT ["sh", "-c", "java -jar /remotefetchparse-0.0.1-SNAPSHOT"]