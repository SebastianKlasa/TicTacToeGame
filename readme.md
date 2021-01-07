# Prison Database Manager

## General information
PrisonDatabaseManager

### Description:
Application designed to managing prison database. This application has limited functionality. It lets to managing prison cells and sectors.

#### Database
Catalog 'database' contain database scheme and example data.
To start the application you have to create database before and edit 'persistence.xml' file, add correct:
- jdbc.url
- jdbc.user
- jdbc.password

#### Tests
Application has tests that has to be passed to build project.

### Features
- view sectors list
- view cells list inside chosen sector
- add new cell/sector
- delete cell/sector (when you delete cell you delete also all cells inside)

### Technologies
- Java 8
- JavaFX
- JPA
- SQL
- MySQL

## Setup
Requirements:
- Maven
- Java 8 or higher
- MySQL server
- Before run application there is required to create database
To build this project locally open project directory and run:
```
mvn package
```
To run program from Command Prompt run follow: 
```
java -jar target/prisonDBApp-1.0-SNAPSHOT.jar
```