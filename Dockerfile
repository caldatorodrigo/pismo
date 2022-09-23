FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD build/libs/backtest-0.0.1-SNAPSHOT.jar backtest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/backtest.jar"]