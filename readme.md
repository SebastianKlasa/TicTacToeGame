# TicTac Game

## General information
Game 

### Description:
Classic tic tac game with board 20x20. Victory condition:
- 5 signs horizontally
- 5 signs vertical
- 5 signs diagonally
Both players have 30s. to move.

#### Database
Database created with Hibernate framework.
In application is used H2 database.

### Features
- log in
- register new user
- create new game 
- join created game
- play the game (make move)
- finish game with draw

### Technologies
- Java 8
- Spring Boot
- Spring Security
- Thymaleaf
- Hibernate
- SQL
- H2
- Html
- CSS
- Javaspcript
- ajax

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
To run program from Command Prompt run follow (default program will run at port 8090): 
```
java -jar target/gameTicTac-0.0.1-SNAPSHOT.jar
```