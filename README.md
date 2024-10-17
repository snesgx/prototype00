# File and Directory Stats
Requires Java 21

This is a Spring Boot application, its supposed to start from "backend/" with:

mvn spring-boot:run

It will also try to launch the React application, that is compiled into "backend/src/main/resources/static/", the source code of this compilation is located at:

frontend/

The backend has swagger-ui configured, usually accessed in:

http://127.0.0.1:8081/swagger-ui/index.html
