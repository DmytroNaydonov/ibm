FROM eclipse-temurin:21-jdk-alpine AS test

WORKDIR /ibmProject

RUN apk add --no-cache bash procps curl tar

ENV MAVEN_HOME /usr/share/maven

COPY --from=maven:3.9.6-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

ARG MAVEN_VERSION=3.9.6
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY src src
COPY pom.xml pom.xml
COPY testng.xml testng.xml

RUN mvn verify

FROM ubuntu as allure

ARG allure_version=2.17.3

WORKDIR /ibmProject

COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

COPY --from=test /ibmProject/allure-results ./allure-results

RUN apt update
RUN apt install curl -y
RUN curl -o allure-commandline-${allure_version}.tgz -Ls https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/${allure_version}/allure-commandline-${allure_version}.tgz
RUN tar -zxvf allure-commandline-${allure_version}.tgz -C /opt/
RUN ln -s /opt/allure-${allure_version}/bin/allure /usr/bin/allure
RUN rm -rf allure-commandline-${allure_version}.tgz
RUN apt install default-jdk -y