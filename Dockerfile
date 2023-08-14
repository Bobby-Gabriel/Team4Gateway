#Build gradle stage

FROM gradle:6.6.1 AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle clean build

#Package stage

FROM eclipse-temurin:17-jdk-alpine
ENV JAR_NAME=Team4Gateway.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
EXPOSE 9001
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME
