Task Management Application – Spring Boot + JWT

This project is a simple Task Management REST API built using Spring Boot, JWT authentication, and PostgreSQL.
It demonstrates user login and logout, role-based authorization, and full CRUD operations for tasks.

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

PostgreSQL database integration

Postman collection and environment for API testing

==================================================

DEFAULT USERS (AUTO-CREATED ON APPLICATION STARTUP)

Admin User =
Username: admin,
Password: admin123,
Role: ADMIN

Normal User =
Username: user1,
Password: user123,
Role: USER

==================================================

POSTGRESQL SETUP

1. Install PostgreSQL
https://www.postgresql.org/download/


2. Create database:

CREATE DATABASE task_manager;

3. Configure application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager

spring.datasource.username=postgres

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

server.port=8080

==================================================

RUN APPLICATION

Using IntelliJ IDEA:

Open the project

Run TaskmanagerApplication.java

Using command line:

mvn clean install
mvn spring-boot:run

Application will start at:
http://localhost:8080

==================================================

JWT AUTHENTICATION FLOW

User logs in using username and password

Server generates a JWT token

Client sends JWT token in Authorization header for protected APIs

JWT is stateless (no server-side session storage)

Logout is handled by client by removing the token

==================================================

POSTMAN SETUP

Import Postman Files

The following files are provided in the repository:

postman/TaskManager.postman_collection.json

postman/TaskManager.postman_environment.json

Steps:

Open Postman

Click Import

Import both the collection and environment files

Select environment: TaskManager-Local (top-right dropdown)

Environment Variables

token (initially empty)

baseUrl (optional, e.g. http://localhost:8080
)

==================================================

AUTHENTICATION APIS

1. LOGIN

POST http://localhost:8080/api/auth/login

Request Body:
{
"username": "admin",
"password": "admin123"
}

Postman Scripts → Post-response:
let jsonData = pm.response.json();
pm.environment.set("token", jsonData.token);


2. LOGOUT

POST http://localhost:8080/api/auth/logout

Postman Scripts → Post-response:
pm.environment.unset("token");

Note:
JWT is stateless. Logout removes the token from the client only.

==================================================

TASK APIS

All task APIs require the following header:

Authorization: Bearer {{token}}


1. VIEW TASKS

GET http://localhost:8080/api/tasks

Request Body: empty


2. CREATE TASK (ADMIN ONLY)

POST http://localhost:8080/api/tasks

Request Body:
{
"title": "First Task",
"description": "Test task",
"status": "OPEN"
}


3. UPDATE TASK

PUT http://localhost:8080/api/tasks/{id}

Example:
http://localhost:8080/api/tasks/9

Request Body:
{
"title": "Updated Task",
"description": "Updated description",
"status": "DONE"
}


4. DELETE TASK (ADMIN ONLY)

DELETE http://localhost:8080/api/tasks/{id}

Example:
http://localhost:8080/api/tasks/7

Response:
{
"message": "Task deleted successfully"
}


5. ASSIGN TASK (ADMIN ONLY)

PUT http://localhost:8080/api/tasks/{taskId}/assign/{userId}

Example:
http://localhost:8080/api/tasks/5/assign/2

==================================================

LOGOUT BEHAVIOR EXPLANATION

After calling the logout API, the user can still access protected APIs if the JWT token is still present in Postman.

This is expected behavior because:

JWT authentication is stateless

The server does not invalidate existing tokens

Token removal is the responsibility of the client

Once the token is removed from Postman, protected APIs will return:
401 Unauthorized

==================================================

GITHUB UPLOAD COMMANDS

git init

git add .

git commit -m "Task Manager API with JWT authentication"

git branch -M main

git remote add origin https://github.com/your-username/taskmanager.git

git push -u origin main

==================================================

ASSESSMENT COMPLETION CHECKLIST

JWT authentication implemented

Role-based authorization implemented

CRUD operations implemented

PostgreSQL database used

Postman collection and environment provided

README documentation completed

==================================================

END OF README