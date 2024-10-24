@echo off
REM Compile the project
mvn clean compile

REM Run the main class
java -cp target\classes org.demo.player.Main
