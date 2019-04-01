FROM hub.c.163.com/library/java:8-alpine

MAINTAINER MICK ideacoding@163.com

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]