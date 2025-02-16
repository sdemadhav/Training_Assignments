FROM openjdk:11-jdk-slim

WORKDIR /app
COPY EmpManageApp.java /app/EmpManageApp.java
COPY pg_driver/postgresql-42.7.5.jar /app/pg_driver/postgresql-42.7.5.jar

RUN javac -cp ".:pg_driver/postgresql-42.7.5.jar" EmpManageApp.java

CMD ["java", "-cp", ".:pg_driver/postgresql-42.7.5.jar", "EmpManageApp"]
