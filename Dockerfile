FROM openjdk:17-jdk-slim-buster
WORKDIR /backtest/build
ENTRYPOINT java -jar backtest-0.0.1-snapshot.jar