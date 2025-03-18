# TaskManagerApplication_housemarktech
# Task Manager API

A Spring Boot-based Task Management System with JWT authentication, CRUD operations, pagination, filtering, and task prioritization using a priority queue.

## Instead of Node.js, in Spring Boot, I did the following which is more effective when it comes to backend SpringBoot excels.

``1.Authentication (JWT-based): Used Spring Security with a JwtAuthenticationFilter, UserDetailsService, and JwtUtil for generating & validating tokens.
  2.CRUD Operations for Tasks: Used Spring Boot REST API with TaskController, TaskService, and TaskRepository (Spring Data JPA).
  3.Data Storage: Used MySQL with JPA entities (Task, User) and JpaRepository for database operations.
  4.Pagination & Filtering: Used Spring Data JPA's Pageable and @Query methods in TaskRepository to filter tasks based on priority & status.
  5.Performance Optimization: Used Redis caching with @Cacheable to improve task retrieval speed.
  6.Efficient Scheduling Algorithm: Used a PriorityQueue (Min-Heap) in TaskService to sort tasks dynamically by priority & timestamp.
  7.Testing: Used JUnit & Mockito instead of Jest/PyTest for unit and integration testing.``

## Features
- **User Authentication**: JWT-based authentication for secure API access.
- **CRUD Operations for Tasks**: Create, update, delete, and retrieve tasks.
- **Task Fields**: Title, description, status (pending/completed), priority (low/medium/high).
- **Pagination & Filtering**: Paginated task retrieval with filters for priority and status.
- **Performance Optimization**: Implemented caching (Redis/In-memory) for faster task retrieval.
- **Task Scheduling**: Tasks are sorted based on priority & timestamp using a priority queue.

## Prerequisites
- Java 17+
- Spring Boot
- Maven
- Redis (if caching is enabled)
- PostgreSQL/MySQL (any SQL database)
- Postman (for API testing)

## Setup Instructions
1. Clone the repository:
   ```sh
   git clone https://github.com/RitneshSrivastava/TaskManagerApplication_housemarktech
   
2. Configure application.properties (update DB and Redis details if needed):

   spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   spring.cache.type=redis
   spring.redis.host=localhost
   spring.redis.port=6379
   jwt.secret=your_generated_secret

3.Access the H2 Database Console (if enabled):

 ## http://localhost:8080/h2-console

 4.API Endpoints:
 
  Register: POST /api/register
  Login: POST /api/login
  Create Task: POST /tasks
  Get Tasks (Paginated & Filtered): GET /tasks?page=0&size=5&priority=high&status=pending
  Update Task: PUT /tasks/{id}
  Delete Task: DELETE /tasks/{id}

5. ## Running API Requests in Postman
    ## Register a User:
    URL: POST http://localhost:8080/auth/register
    Body (JSON)
      `` {
             "username": "testuser",
             "password": "password123"
       }``
   
   ## Login and Get Token:
    ``URL: POST http://localhost:8080/register``
    Response contains token, copy it for authentication.
    Create a Task (Use Token in Headers):
    ``URL: POST http://localhost:8080/api/tasks``
   
   ## Authorization: Bearer YOUR_JWT_TOKEN
   
    Content-Type: application/json
  
    ``{
       "title": "New Task",
       "description": "Complete project",
       "status": "pending",
       "priority": "high"
    }``
   
## Retrieve Tasks with Filters & Pagination:
   URL: GET http://localhost:8080/tasks?page=0&size=5&priority=high&status=pending
   Headers:
   Authorization: Bearer YOUR_JWT_TOKEN

## Update Task:

URL: PUT http://localhost:8080/tasks/{id}
Body:
  ``{
    "title": "Updated Task",
    "description": "Updated description",
    "status": "completed",
    "priority": "medium"
    }``

## Delete Task:
URL: DELETE http://localhost:8080/tasks/{id}
Authorization: Bearer YOUR_JWT_TOKEN

## Running Tests
To run unit tests:
`` mvn test``

Deployment
``Build JAR file:``
``mvn clean package``
Run with:
``java -jar target/task-manager-0.0.1-SNAPSHOT.jar``

## Tools Used
    Spring Boot (Backend Framework)
    JWT (Authentication)
    Redis (Caching)
    Priority Queue (Task Scheduling)
    MySQL (Database)
    Postman (API Testing)

    task-management-system/
    
## Project Structure
│-- src/main/java/com/example/taskmanager
│   │-- TaskManagerApplication.java  --> Main Spring Boot application
│   │-- config/
│   │   ├── SecurityConfig.java  --> Configures JWT security
│   │   ├── CacheConfig.java  --> Configures Redis caching
│   │-- controller/
│   │   ├── AuthController.java  --> Handles user authentication
│   │   ├── TaskController.java  --> Handles task CRUD operations
│   │-- dto/
│   │   ├── LoginRequest.java  --> DTO for login
│   │   ├── RegisterRequest.java  --> DTO for user registration
│   │   ├── TaskRequest.java  --> DTO for creating/updating tasks
│   │-- model/
│   │   ├── User.java  --> Entity for users
│   │   ├── Task.java  --> Entity for tasks
│   │-- repository/
│   │   ├── UserRepository.java  --> JPA Repository for User
│   │   ├── TaskRepository.java  --> JPA Repository for Task
│   │-- service/
│   │   ├── AuthService.java  --> Handles authentication logic
│   │   ├── TaskService.java  --> Handles business logic for tasks
│   │   ├── TaskSchedulerService.java  --> Implements the priority queue for scheduling
│   │-- util/
│   │   ├── JwtUtil.java  --> Utility class for JWT operations
│   │-- exception/
│   │   ├── GlobalExceptionHandler.java  --> Handles exceptions globally
│-- src/test/java/com/example/taskmanager
│   │-- AuthControllerTest.java  --> Unit tests for authentication
│   │-- TaskControllerTest.java  --> Unit tests for tasks
│-- pom.xml  --> Maven dependencies

## You can simply use the intellij IDE and then can run the project by cloning the project in it and just hit the run button of the main function.

