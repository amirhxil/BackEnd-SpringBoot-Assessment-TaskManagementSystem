Task Management Application – Spring Boot + JWT

This project is a simple Task Management REST API built using Spring Boot, JWT authentication, and PostgreSQL. It demonstrates login and logout, role-based authorization, and full CRUD operations for tasks.

==================================================

TECH STACK

Java 21+
Spring Boot 3.5.x
Spring Security
JWT (JSON Web Token)
PostgreSQL
Spring Data JPA
Maven
Postman

==================================================

FEATURES

User login and logout
JWT-based authentication
Role-based authorization (ADMIN / USER)
Create task
Update task
Assign task
View tasks
Delete task
PostgreSQL database
Postman collection for API testing

==================================================

DEFAULT USERS (AUTO-CREATED ON STARTUP)

Admin User
Username: admin
Password: admin123
Role: ADMIN

Normal User
Username: user1
Password: user123
Role: USER

==================================================

POSTGRESQL SETUP

Install PostgreSQL from:
https://www.postgresql.org/download/

Create database:
CREATE DATABASE taskmanager;

Configure application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

==================================================

RUN APPLICATION

Using IntelliJ IDEA:
Open project and run TaskmanagerApplication.java

Using command line:
mvn clean install
mvn spring-boot:run

Application runs at:
http://localhost:8080

==================================================

JWT AUTHENTICATION FLOW

Login generates JWT token
Token must be sent in Authorization header
JWT is stateless (server does not store session)
Logout is handled by client by deleting token

==================================================

POSTMAN SETUP

Create a Postman Environment:
Name: TaskManager-Local

Add environment variable:
token (leave value empty)

==================================================

AUTHENTICATION APIS

LOGIN

POST http://localhost:8080/api/auth/login

Body:
{
"username": "admin",
"password": "admin123"
}

Postman Scripts → Post-response:
let jsonData = pm.response.json();
pm.environment.set("token", jsonData.token);

LOGOUT

POST http://localhost:8080/api/auth/logout

Postman Scripts → Post-response:
pm.environment.unset("token");

Note:
JWT is stateless. Logout only removes token from client.

==================================================

TASK APIS

ALL TASK APIS REQUIRE HEADER:
Authorization: Bearer {{token}}

VIEW TASKS

GET http://localhost:8080/api/tasks

Body: empty

CREATE TASK (ADMIN ONLY)

POST http://localhost:8080/api/tasks

Body:
{
"title": "First Task9",
"description": "Test task",
"status": "OPEN"
}

UPDATE TASK

PUT http://localhost:8080/api/tasks/{id}

Example:
http://localhost:8080/api/tasks/9

Body:
{
"title": "Updated Tas9999",
"description": "Updated",
"status": "DONE"
}

DELETE TASK (ADMIN ONLY)

DELETE http://localhost:8080/api/tasks/{id}

Example:
http://localhost:8080/api/tasks/7

Body: empty

Response:
{
"message": "Task deleted successfully"
}

ASSIGN TASK (ADMIN ONLY)

PUT http://localhost:8080/api/tasks/{taskId}/assign/{userId}

Example:
http://localhost:8080/api/tasks/5/assign/2

==================================================

LOGOUT BEHAVIOR EXPLANATION

After calling logout API, the user can still access APIs if the JWT token is still present in Postman.

This is expected because:
JWT is stateless
Server does not invalidate tokens
Client must remove token

Once the token is removed, protected APIs will return 401 Unauthorized.

==================================================

GITHUB UPLOAD COMMANDS

git init
git add .
git commit -m "Task Manager API with JWT authentication"
git branch -M main
git remote add origin https://github.com/your-username/taskmanager.git

git push -u origin main

==================================================

ASSESSMENT COMPLETION

JWT Authentication implemented
Role-based authorization implemented
CRUD operations implemented
PostgreSQL database used
Postman testing prepared
README documentation completed

==================================================

END OF README