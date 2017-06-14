FROM maven:3.5.0-jdk-8

RUN mkdir lucenelanches && cd lucenelanches

ADD . lucenelanches

WORKDIR lucenelanches

RUN mvn package && java -jar target/lucenelanches-0.1.0.jar

EXPOSE 8080
