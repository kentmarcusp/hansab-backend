# Homework Project for Hansab

This is a Spring Boot project for handling homework assignments. It uses Java 19, Maven for build management, and includes various dependencies such as Spring Data JPA, H2Db and Flyway for database migrations.

## Prerequisites

- Java 19
- Maven 3.6.3 or later
- H2Database

## Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/kentmarcusp/hansab-homework.git
cd homework
```
Ensure Java is installed
```bash
java -version
# Recommended to use at least java version "19"
# Ensure your JAVA_HOME env variable is set to the Java 19 installation path.
```

### 2. Configure application properties
The application is configured to default into using H2 Database.  
Database configuration is located in src/main/resources/application.properties of the project.  

The H2 Database is an in-memory database, meaning that data is erased and re-initialized each time the project is rebuilt. 
You can view the contents of the database using the H2 Console, which is accessible by navigating to http://localhost:8080/h2-console.  
Keep in mind :8080 is the default port, but feel free to replace that with your own running port number.

### 3. Build and Run the application
Use Maven to properly build and run the application in the root folder:
```bash
mvn clean install
mvn spring-boot:run
```

### 4. Running Tests
To run all the tests:
```bash
mvn test
```


## Project Structure
**src/main/java:** Contains the main application code.  
**src/main/resources:** Contains the application.properties file, Fly-way migration files and other resource files.  
**src/test/java:** Contains the test cases for the application.

## Main Dependencies
**Spring Boot Starter Data JPA:** Basic database interaction.  
**Spring Boot Starter Web:** For building web applications.  
**H2 Database:** In-memory database for development and testing.  
**Lombok:** To reduce boilerplate code.  
**Spring Web:** For building web applications.  
**Flyway Core:** For database migrations.  
**Spring Boot Starter Test:** For testing Spring Boot applications.  
**Mockito Core:** For mocking in tests.  
