# 1. Java 11�� ����� Spring Boot ���̽� �̹��� ����
FROM openjdk:11-jdk-slim

# 2. ����� jar ������ �����̳� ���η� ����
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. ��Ʈ ����
EXPOSE 8877

# 4. ���ø����̼� ����
ENTRYPOINT ["java","-jar","/app.jar"]