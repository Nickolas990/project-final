FROM openjdk:17
MAINTAINER Nickolay Melnikov (Echo)
COPY target/jira-1.0.jar jira-1.0.jar
ENTRYPOINT ["java","-jar","/jira-1.0.jar"]