FROM maven:3.8.6-amazoncorretto-17 as builder

WORKDIR /app
COPY ./ /app

RUN mvn -DskipTests=true clean
RUN mvn -B -DskipTests install

RUN mkdir /app-jar-output
RUN cp /app/target/*.jar /app-jar-output

FROM openjdk:17-bullseye

ENV JAVA_OPTS ""
ENV PROJECT_NAME discovery
ENV TZ America/Sao_Paulo
ENV TIMEZONE America/Sao_Paulo

WORKDIR /tmp

RUN mkdir /opt/${PROJECT_NAME}
WORKDIR /opt/${PROJECT_NAME}

RUN apt-get update -y
RUN apt-get install -y tzdata

COPY --from=builder /app-jar-output/ .

ENTRYPOINT java ${JAVA_OPTS} -jar $(ls *.jar)