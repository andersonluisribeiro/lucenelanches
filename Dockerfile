FROM maven:3.5.0-jdk-8

RUN mkdir lucenelanches && cd lucenelanches

ADD . lucenelanches

WORKDIR lucenelanches

RUN mvn package

EXPOSE 8080
